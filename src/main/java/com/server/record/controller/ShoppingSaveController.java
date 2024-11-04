package com.server.record.controller;

import com.server.record.domain.*;
import com.server.record.service.ProductService;
import com.server.record.service.ShoppingSaveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shoppingSave/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class ShoppingSaveController {

    @Autowired
    private ShoppingSaveService service;

    @Autowired
    private ProductService productService;

    // 찜하기 생성하기
    @PostMapping("createShoppingSave")
    public ResponseEntity create(@RequestBody ShoppingSave shoppingSave){
        service.createShoppingSave(shoppingSave);
        return ResponseEntity.ok().build();
    };
    // 삭제하기 ( 디테일 페이지에서 삭제하기 )
    @DeleteMapping("deleteShoppingSave")
    public ResponseEntity deleteShoppingSave (@RequestParam(name="userCode") int userCode,@RequestParam(name="productCode") int productCode){

//        log.info("1. 유저 코드 : "+ userCode);
//        log.info("2. 상품 코드 : "+ productCode);
        ShoppingSave shoppingSave = ShoppingSave.builder()
                .productCode(productCode)
                .userCode(userCode)
                .build();
//        log.info("3. 상품 정보 : "+ shoppingSave);
        ShoppingSave userProductSave = service.userMemberSaveCheck(shoppingSave);

        service.DeleteProductSave(userProductSave.getShoppingCode());
        return ResponseEntity.ok().build();
    }

    //////
    // 바로 결제하기 눌렀을때 생성되고
    @PostMapping("createShoppingSaveOrder")
    public ResponseEntity createShoppingSaveOrder(@RequestBody ShoppingSaveOrder shoppingSaveOrder){
        service.createShoppingSaveOrder(shoppingSaveOrder);
        return ResponseEntity.ok().build();
    }
    // 결제하기 할때 만들어졌으면 결제 페이지에서 보여주기
    @GetMapping("createShoppingSaveOrderView/{userCode}")
    public ResponseEntity createShoppingSaveOrderView(@PathVariable int userCode){
        log.info("1.1 정보들 : "+userCode);
        // 코드로 정보 가져오기
        ShoppingSaveOrder shoppingSaveOrder = service.viewCreateSaveOrder(userCode);
        log.info("1. 정보들 : "+shoppingSaveOrder);

        //상품코드로 찾아서 정보 가져와야함
        Product product = productService.detailInformation(shoppingSaveOrder.getProductCode());
        log.info("2. 정보들 : "+product);
        // 상품코드로 이미지 가져오기
//        List<ProductImg> productImgs = productService.AllViewLpImg(product.getProductCode());
//        log.info("3. 정보들 : "+productImgs);


        return ResponseEntity.ok().body(0);
    }
    // 결제페이지 나가면 바로 삭제되게끔 만들기
    @DeleteMapping("createShoppingSaveOrderDelete")
    public ResponseEntity createShoppingSaveOrderDelete(@RequestParam(name="userCode") int userCode,@RequestParam(name="productCode") int productCode){
        ShoppingSaveOrder shoppingSaveOrder = ShoppingSaveOrder.builder()
                .userCode(userCode)
                .productCode(productCode)
                .build();
        service.deleteCreateSaveOrder(shoppingSaveOrder);

        return ResponseEntity.ok().build();
    }
    //////

    // 유저 찜목록 체크 여부
    @GetMapping("/allViewShoppingSave/{userCode}")
    public ResponseEntity AllViewShoppingSave(@PathVariable int userCode){
        return ResponseEntity.ok().body(service.AllViewShoppingSave(userCode));
    }




}
