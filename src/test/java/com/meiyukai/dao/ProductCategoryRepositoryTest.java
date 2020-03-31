package com.meiyukai.dao;

import com.meiyukai.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Resource(name = "productCategoryRepository")
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void testFindAll(){

        List<ProductCategory> productCategoryList = productCategoryRepository.findAll();
        for (ProductCategory pc : productCategoryList){
            System.out.println("productCategory :   "   + pc  );
        }

    }



    @Test
    public void testFindById(){
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(1);
        System.out.println("productCategory :  "  + productCategory.get());
    }


    @Test
//    @Rollback(value = false)
    @Transactional
    public void testSaveProduct(){
        ProductCategory category  =  new ProductCategory();
        category.setCategoryName("没人爱他");
        category.setCategoryType(10);
        productCategoryRepository.save(category);
    }



    @Test
    @Transactional
    @Rollback(value = false)
    public void testDeleteCategory(){

        Specification<ProductCategory> specification  =  new Specification<ProductCategory>() {
            @Override
            public Predicate toPredicate(Root<ProductCategory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> categoryId = root.get("categoryId");
                Predicate predicate = criteriaBuilder.equal(categoryId, 1);
                return predicate;
            }
        };

        Optional<ProductCategory> category  =  productCategoryRepository.findOne(specification);
        ProductCategory productCategory = category.get();
        productCategoryRepository.delete(productCategory);
        System.out.println("productCategory  :  "  + productCategory);

    }


    @Test
    public void testUpdateCategory(){
        ProductCategory pc =  productCategoryRepository.findById(1).get(); // 先查寻 ，再修改
//        pc.setCategoryType(18);
        pc.setCategoryName("男生最爱");
        productCategoryRepository.save(pc);
    }



    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> categoryTypeList = new ArrayList<>();
        categoryTypeList.add(8);
        categoryTypeList.add(10);

        List<ProductCategory> categoryList = productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
//        Assert.assertNotNull(categoryList);

        Assert.assertNotEquals(null ,  categoryList);

       /* for (ProductCategory category : categoryList){
            System.out.println("==============  category :   "  + category);
        }*/

    }


    @Test
    public void findByCategoryTypeTest(){
        ProductCategory productCategory = productCategoryRepository.findByCategoryType(1);
        System.out.println("categoryName : " + productCategory.getCategoryName());
    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void findCategoryIdList(){
        List<Integer> list = productCategoryRepository.findCategoryIdList();
      System.out.println("list: " + list);
    }












}