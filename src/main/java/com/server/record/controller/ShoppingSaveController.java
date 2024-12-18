package com.server.record.controller;

import com.server.record.domain.*;
import com.server.record.service.ProductService;
import com.server.record.service.ShoppingSaveService;
import com.server.record.service.UserTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private UserTableService userTableService;

    ///////////////////// ( 유저가 바로 결제페이지로 들어갔을떄 )
    // 바로 결제하기 눌렀을때 생성되고
    @PostMapping("createShoppingSaveOrder")
    public ResponseEntity createShoppingSaveOrder(@RequestBody ShoppingSaveOrder shoppingSaveOrder){
        service.createShoppingSaveOrder(shoppingSaveOrder);
        return ResponseEntity.ok().build();
    }
    // 결제하기 할때 만들어졌으면 결제 페이지에서 보여주기
    @GetMapping("createShoppingSaveOrderView/{userCode}")
    public ResponseEntity createShoppingSaveOrderView(@PathVariable int userCode){
            // 코드로 정보 가져오기
            ShoppingSaveOrder shoppingSaveOrder = service.viewCreateSaveOrder(userCode);

            //상품코드로 찾아서 정보 가져와야함
            Product product = productService.detailInformation(shoppingSaveOrder.getProductCode());
            // 상품코드로 이미지 가져오기
            List<ProductImg> productImgs = productService.AllViewImg(product.getProductCode());

            ShoppingSaveOrderDTO shoppingSaveOrderDTO = ShoppingSaveOrderDTO.builder()
                    .shoppingOrderCode(shoppingSaveOrder.getShoppingOrderCode())
                    .product(product)
                    .productImg(productImgs.get(0).getProductImgAddress())
                    .build();
            return ResponseEntity.ok().body(shoppingSaveOrderDTO);
        }

    // 결제페이지 나가면 바로 삭제되게끔 만들기 or 결제 취소 눌렀을때
    @DeleteMapping("createShoppingSaveOrderDelete")
    public ResponseEntity createShoppingSaveOrderDelete(@RequestParam(name="userCode") int userCode,
                                                        @RequestParam(name="productCode") int productCode){
        ShoppingSaveOrder shoppingSaveOrder = ShoppingSaveOrder.builder()
                .userCode(userCode)
                .productCode(productCode)
                .build();
        service.deleteCreateSaveOrder(shoppingSaveOrder);

        return ResponseEntity.ok().build();
    }

    // 결제하기 눌렀을떄 상황
    @PostMapping("createProductOrder")
    public ResponseEntity createProductOrder(@RequestBody ShoppingSaveOrder shoppingSaveOrder){
//        log.info("값 들어왓나 ? : " + shoppingSaveOrder);
        // 수량 하나 지우기
        Product product = productService.detailInformation(shoppingSaveOrder.getProductCode());
        int count = product.getProductQuantity();
        product.setProductQuantity(count - 1);
        productService.productUpData(product);

        // 이제 생성이 되면서
        service.createProductOrder(shoppingSaveOrder);
        // 삭제되게끔
        service.deleteCreateSaveOrder(shoppingSaveOrder);
        return ResponseEntity.ok().build();
    }

    // 장바구니 생성하기 ( 디테일 페이지 )
    @PostMapping("createShoppingSave")
    public ResponseEntity create(@RequestBody ShoppingSaveDTO shoppingSaveDto){
            service.createShoppingSave(ShoppingSave.builder()
                    .productCode(shoppingSaveDto.getProductCode())
                    .userCode(shoppingSaveDto.getUserCode())
                    .build());
        return ResponseEntity.ok().build();
    };
    // 삭제하기 ( 디테일 페이지에서 삭제하기 )
    @DeleteMapping("deleteShoppingSave")
    public ResponseEntity deleteShoppingSave (@RequestParam(name="userCode") int userCode,
                                              @RequestParam(name="productCode") int productCode){
            ShoppingSave shoppingSave = ShoppingSave.builder()
                    .productCode(productCode)
                    .userCode(userCode)
                    .build();
            ShoppingSave userProductSave = service.userMemberSaveCheck(shoppingSave);
            service.DeleteProductSave(userProductSave.getShoppingCode());
        return ResponseEntity.ok().build();
    }


    // ( 장바구니 )결제하기 눌렀을떄 상황
    @PostMapping("createProductOrders")
    public ResponseEntity createProductOrders(@RequestBody ShoppingSaveOrderDTO shoppingSaveOrderDTO){
        for(int i =0;i<shoppingSaveOrderDTO.getProductCode().length;i++ ){
            Product product = productService.detailInformation(shoppingSaveOrderDTO.getProductCode()[i]);
            int count = product.getProductQuantity();
            product.setProductQuantity(count - 1);
            productService.productUpData(product);
            ShoppingSaveOrder shoppingSaveOrder = ShoppingSaveOrder.builder()
                    .userCode(shoppingSaveOrderDTO.getUserCode())
                    .addressCode(shoppingSaveOrderDTO.getAddressCode())
                    .productCode(shoppingSaveOrderDTO.getProductCode()[i])
                    .build();
            service.createProductOrder(shoppingSaveOrder);
            service.deleteCreateSaveOrders(shoppingSaveOrder);
        }
        return ResponseEntity.ok().build();
    }



    /////////////////////
    ///////////////////// ( 장바구니에서 결제페이지로 들어갔을때 )
    // 헤더에 장바구니 (5) 이런식으로 나오게끔 숫자 뽑아내기
    @GetMapping("createShoppingSaveViewList/{userCode}")
    public ResponseEntity createShoppingSaveViewList(@PathVariable int userCode){
        List<ShoppingSave> shoppingSave = service.AllViewShoppingSave(userCode);
//        log.info("" + shoppingSave.size());
        return ResponseEntity.ok().body(shoppingSave.size());
    }
    // 장바구니에서 삭제하기 눌렀을때 삭제되게끔 만들기
    @DeleteMapping("createShoppingSaveDelete")
    public ResponseEntity createShoppingSaveDelete(@RequestParam(name="shoppingCode") int shoppingCode) {
//        log.info("삭제 : " + shoppingCode);
        service.deleteCreateSave(shoppingCode);
        return ResponseEntity.ok().build();
    }
    // 합계 금액 보여주기
    @GetMapping("viewOrderPrice")
    public ResponseEntity viewOrderPrice(@RequestParam(name="userCode") int userCode) {
        log.info("1. " + userCode);
        List<Integer> price = service.viewOrderPrice(userCode);
        int sumPrice = 0;
        for(Integer sum : price){
            sumPrice += sum;
        }
        log.info("2. " + sumPrice);
        return ResponseEntity.ok().body(sumPrice);
    }

    /////////////////////

    // 유저 찜목록 체크 여부 ( 정확히는 유저가 추가한 장바구니 뽑아서 보여주기 )
    @GetMapping("/allViewShoppingSave/{userCode}")
    public ResponseEntity AllViewShoppingSave(@PathVariable int userCode){
//        log.info("" + userCode);
        // 유저 코드로 뽑아온걸 다시 담아서 보내기
        List<ShoppingSave> shoppingSave = service.AllViewShoppingSave(userCode);
        List<ShoppingSaveDTO> productList = new ArrayList<>();
        for(ShoppingSave save : shoppingSave){
            // 뽑아온 코드로 다시 정보 찾아오고
            // DTO로 담아서 보내주기
            Product product = service.ShoppingProductView(save.getProductCode());

            List<ProductImg> productImgs = productService.AllViewImg(product.getProductCode());

            ShoppingSaveDTO shoppingSaveDTO = ShoppingSaveDTO.builder()
                    .shoppingCode(save.getShoppingCode())
                    .userCode(save.getUserCode())
                    .product(product)
                    .productImg(productImgs.get(0).getProductImgAddress())
                    .build();
            productList.add(shoppingSaveDTO);
        }

        return ResponseEntity.ok().body(productList);
    }

}
