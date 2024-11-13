package com.server.record.controller;

import com.server.record.domain.*;
import com.server.record.service.ProductService;
import com.server.record.service.ShoppingSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/product/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ShoppingSaveService shoppingSaveService;


    private String url = "\\\\192.168.10.51\\record\\";


    // 이 두개는 메인페이지에서 10개정도만 보여는거
    // LP
    @GetMapping("MainLP")
    public ResponseEntity MainLP(){
        List<ProductDTO> list = new ArrayList<>();
        for(Product product : service.MainLP()){
            List<ProductImg> mainImg = service.AllViewImg(product.getProductCode());

            ProductDTO dto = ProductDTO.builder()
                    .productCode(product.getProductCode())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .productType(product.getProductType())
                    .productImgOne(mainImg.get(0).getProductImgAddress())
                    .productQuantity(product.getProductQuantity())
                    .build();
            list.add(dto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    // Record
    @GetMapping("MainRecord")
    public ResponseEntity MainRecord(){
        List<ProductDTO> list = new ArrayList<>();
        for(Product product : service.MainRecode()){
            List<ProductImg> mainImg = service.AllViewImg(product.getProductCode());

            ProductDTO dto = ProductDTO.builder()
                    .productCode(product.getProductCode())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .productType(product.getProductType())
                    .productImgOne(mainImg.get(0).getProductImgAddress())
                    .productQuantity(product.getProductQuantity())
                    .build();
            list.add(dto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    ////////////////////////////////////////////////////////////////////////////////////////// LP 및 레코드 페이징 및 보여주기
    // LP상품 다보여주기
    @GetMapping("AllView")
    public ResponseEntity AllViewLp(@RequestParam("no")int no, @RequestParam("productType")String productType) {
        if(productType.equals("LP")){
            List<Product> product = service.AllPagingViewLp(no);
            List<ProductDTO> products = new ArrayList<>();
            for(Product Pd : product){
                List<ProductImg> proImg= service.AllViewImg(Pd.getProductCode());
                ProductDTO dto = ProductDTO.builder()
                        .productCode(Pd.getProductCode())
                        .productType(Pd.getProductType())
                        .productName(Pd.getProductName())
                        .productPrice(Pd.getProductPrice())
                        .productExplanation(Pd.getProductExplanation())
                        .productQuantity(Pd.getProductQuantity())
                        .productImgOne(proImg.get(0).getProductImgAddress())
                        .build();
                products.add(dto);
            }
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        if(productType.equals("레코드")){
            List<Product> product = service.AllPagingViewRecord(no);
            List<ProductDTO> products = new ArrayList<>();
            for(Product Pd : product){
                List<ProductImg> proImg= service.AllViewImg(Pd.getProductCode());
                ProductDTO dto = ProductDTO.builder()
                        .productCode(Pd.getProductCode())
                        .productType(Pd.getProductType())
                        .productName(Pd.getProductName())
                        .productPrice(Pd.getProductPrice())
                        .productExplanation(Pd.getProductExplanation())
                        .productQuantity(Pd.getProductQuantity())
                        .productImgOne(proImg.get(0).getProductImgAddress())
                        .build();
                products.add(dto);
            }
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    //LP 상품 페이지
    @GetMapping("TotalPage/{type}")
    public ResponseEntity TotalPage (@PathVariable String type){
        if(type.equals("LP")){
            return ResponseEntity.ok().body(service.AllViewLp());
        }
        if(type.equals("레코드")){
            return ResponseEntity.ok().body(service.AllViewRecode());
        }
        return ResponseEntity.ok().build();
    }


    //////////////////////////////////////////////////////////////////////////////////////////
    //디테일 LP상품 코드 보여주기
    @GetMapping("DetailView/{code}")
    public ResponseEntity DetailView(@PathVariable int code, @RequestParam(name="userCode") int userCode){
//        log.info("1. 정보가져오기 : " + productType);

            Product product = service.detailInformation(code);
//        log.info("1. 정보가져오기 : " + product);
            List<ProductImg> productImg = service.AllViewImg(code);
//        log.info("2. 코드로 이미지들 싹다 가져오기 : " + productImg);

            // 코드로 추천하기 count로 가져오기
            int productLike = service.productLike(code);
            log.info(""+productLike);

            ProductDTO productDTO = ProductDTO.builder()
                    .productCode(product.getProductCode())
                    .productType(product.getProductType())
                    .productName(product.getProductName())
                    .productPrice(product.getProductPrice())
                    .productExplanation(product.getProductExplanation())
                    .productQuantity(product.getProductQuantity())
                    .productLongtext(product.getProductLongtext())
                    .productImgAll(productImg)
                    .productSub(productLike)
                    .build();

            // 장바구니 체크용
            ShoppingSave save = ShoppingSave.builder()
                    .productCode(code)
                    .userCode(userCode)
                    .build();
            ShoppingSave shoppingSave = shoppingSaveService.userMemberSaveCheck(save);
            if(shoppingSave!=null) {
                productDTO.setPageCheck(true);
            }
            // 추천하기 체크용
            ProductLike productLikeCheck = ProductLike.builder()
                    .productCode(code)
                    .userCode(userCode)
                    .build();
            if(productLikeCheck != null){
                productDTO.setProductSubCheck(true);
            }


            return ResponseEntity.ok().body(productDTO);

    }





    
    // 제품 만들기 ( LP, 레코드 )
    @PostMapping("CreateLpRecordProduct")
    public ResponseEntity createProduct(ProductDTO dto){

        try{
            Product pro = Product.builder()
                    .productType(dto.getProductType())
                    .productName(dto.getProductName())
                    .productPrice(dto.getProductPrice())
                    .productExplanation(dto.getProductExplanation())
                    .productQuantity(dto.getProductQuantity())
                    .productLongtext(dto.getProductLongtext())
                    .build();
            Product productCode = service.CreateLpProduct(pro);

            Path directoryPath = Paths.get(url + "\\Product\\" + dto.getProductType());
            Path directoryPathType = Paths.get(url + "\\Product\\" + dto.getProductType() + "\\"+ productCode.getProductCode());

            Files.createDirectories(directoryPath);
            Files.createDirectories(directoryPathType);

            log.info("1. "+ productCode);
            for(int i=0;i<dto.getProductImg().length;i++){
                String UUIDFileName = fileUpload(dto.getProductImg()[i],productCode);
                ProductImg img = ProductImg.builder()
                        .productCode(productCode.getProductCode())
                        .productImgAddress(UUIDFileName)
                        .build();
                service.CreateImpProduct(img);
            }
        }catch(Exception e){
            log.info("" + e);
        }
        return ResponseEntity.ok().build();
    }

    // 가져온 파일을 UUID 로 랜덤 이름값 같이 붙여서 만들어주기
    public String fileUpload(MultipartFile file, Product product) throws IOException {
        UUID uuid = UUID.randomUUID();
        String fileName = uuid.toString()+"_"+file.getOriginalFilename();
        File copyFile = new File(url +File.separator + "Product"+ File.separator + product.getProductType() +
                                                    File.separator + product.getProductCode() + File.separator + fileName);
        file.transferTo(copyFile);
        return fileName;
    }




}






