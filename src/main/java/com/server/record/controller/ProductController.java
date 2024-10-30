package com.server.record.controller;

import com.server.record.domain.Product;
import com.server.record.domain.ProductDTO;
import com.server.record.domain.ProductImg;
import com.server.record.service.ProductService;
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


    private String url = "\\\\192.168.10.51\\record\\";


    // 이 두개는 메인페이지에서 12개정도만 보여는거
    @GetMapping("MainLP")
    public ResponseEntity MainLP(){
        return ResponseEntity.status(HttpStatus.OK).body(service.MainLP());
    }
    @GetMapping("MainRecord")
    public ResponseEntity MainRecord(){
        return ResponseEntity.status(HttpStatus.OK).body(service.MainRecode());
    }

    //디테일 LP상품 코드 보여주기
    @GetMapping("DetailViewLp/{code}")
    public ResponseEntity DetailLp(@PathVariable int code){
        Product product = service.detailInformation(code);
//        log.info("1. 정보가져오기 : " + product);
        List<ProductImg> productImg = service.AllViewLpImg(code);
//        log.info("2. 코드로 이미지들 싹다 가져오기 : " + productImg);
        ProductDTO productDTO = ProductDTO.builder()
                .productCode(product.getProductCode())
                .productType(product.getProductType())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productExplanation(product.getProductExplanation())
                .productQuantity(product.getProductQuantity())
                .productLongtext(product.getProductLongtext())
                .productImgAll(productImg)
                .build();

        return ResponseEntity.ok().body(productDTO);
    }

    // LP상품 다보여주기
    @GetMapping("AllViewLp/{no}")
    public ResponseEntity AllViewLp(@PathVariable int no) {
        log.info("" + no);
        // 서버에서 가져왔지만 이미지는 없기때문에 다시 product에 넣고
        List<Product> product = service.AllPagingViewLp(no);
        List<ProductDTO> products = new ArrayList<>();
        // 배열로 왔으니 반복문 돌려서
        for(Product Pd : product){
            //해당 코드로 서버에 있는 이미지 매핑? 하기
            List<ProductImg> proImg= service.AllViewLpImg(Pd.getProductCode());

//            // 이미지를 가져왔으면 다시 배열로 담아서
//            // 근데 왜 이렇게 했냐 라고 하면 이미지가 2장 이상이기 때문에
//            String[] img = new String[proImg.size()];
//            for(int i =0;i<proImg.size();i++){
//                img[i] = proImg.get(i).getProductImgAddress();
//            }
            //dto 에 넣어주기
            ProductDTO dto = ProductDTO.builder()
                    .productCode(Pd.getProductCode())
                    .productType(Pd.getProductType())
                    .productName(Pd.getProductName())
                    .productPrice(Pd.getProductPrice())
                    .productExplanation(Pd.getProductExplanation())
                    .productQuantity(Pd.getProductQuantity())
                    .productImgOne(proImg.get(0).getProductImgAddress())
                    .build();
            log.info("2. " + dto);
            products.add(dto);

        }
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
    //LP 상품 카운터
    @GetMapping("TotalPage")
    public ResponseEntity totalPage(){
        return ResponseEntity.ok().body(service.AllViewLp());
    }

    @GetMapping("AllViewRecord")
    public ResponseEntity AllViewRecord() {

        return ResponseEntity.status(HttpStatus.OK).body(service.AllViewRecode());
    }


    
    // 제품 만들기 ( LP, 레코드 )
    @PostMapping("CreateLpRecordProduct")
    public ResponseEntity createProduct(ProductDTO dto){
        // 일단 에디터에 들어온 이미지랑 테그들은 잘 들어옴

        Path directoryPath = Paths.get(url + "\\Product\\" + dto.getProductType());
        Path directoryPathType = Paths.get(url + "\\Product\\" + dto.getProductType() + "\\"+ dto.getProductName());
        try{
            //집에서 할때만 잠깐 끄기
            Files.createDirectories(directoryPath);
            Files.createDirectories(directoryPathType);
            // 빌드로 넣어주고
            Product pro = Product.builder()
                    .productType(dto.getProductType())
                    .productName(dto.getProductName())
                    .productPrice(dto.getProductPrice())
                    .productExplanation(dto.getProductExplanation())
                    .productQuantity(dto.getProductQuantity())
                    .productLongtext(dto.getProductLongtext())
                    .build();
            // 서버에 보내준다음 코드를받아
            int ProductCode = service.CreateLpProduct(pro);

            // 그코드를 이미지에 넣어주고 이미지가 2장이상미면 이미지 갯수만큼 반복문 돌려서 이미지 갯수만큼 컬럼만들어주기
            for(int i=0;i<dto.getProductImg().length;i++){
                String UUIDFileName  = fileUpload(dto.getProductImg()[i],pro);
                ProductImg img = ProductImg.builder()
                        .productCode(ProductCode)
                        .productImgAddress(UUIDFileName)
                        .build();
                // 서버에 만들면서 파일 업로드하기
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
        // 멀티파일로 가져온 이름 오리지널로 바꿔주면서 저장하기
        String fileName = uuid.toString()+"_"+file.getOriginalFilename();
        //집에서 할때만 잠깐 끄기
        File copyFile = new File(url +File.separator + "Product"+ File.separator + product.getProductType() + File.separator + product.getProductName() + File.separator + fileName);
        file.transferTo(copyFile);
        return fileName;
    }
}
