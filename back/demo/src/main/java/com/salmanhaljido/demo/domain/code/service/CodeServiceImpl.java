package com.salmanhaljido.demo.domain.code.service;

import com.salmanhaljido.demo.domain.code.entity.DongCode;
import com.salmanhaljido.demo.domain.code.entity.GuGunCode;
import com.salmanhaljido.demo.domain.code.entity.SiDoCode;
import com.salmanhaljido.demo.domain.code.repository.DongCodeRepository;
import com.salmanhaljido.demo.domain.code.repository.GuGunCodeRepository;
import com.salmanhaljido.demo.domain.code.repository.SiDoCodeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{

    String dataPath = "src/main/resources/data/";
    //String dataPath = "C:\\Users\\leekijong\\S07P22D110\\back\\demo\\src\\main\\resources\\data\\";
    private final SiDoCodeRepository siDoCodeRepository;
    private final DongCodeRepository dongCodeRepository;
    private final GuGunCodeRepository guGunCodeRepository;

    @Override
    @Transactional
    @PostConstruct
    public void getData() {
        Map<String, String> sidoMap = new HashMap<>();
        sidoMap.put("서울특별시", "37.5666103,126.9783882");
        sidoMap.put("강원도", "37.8603672,128.3115261");
        sidoMap.put("경기도", "37.4363177,127.550802");
        sidoMap.put("경상남도", "35.4414209,128.2417453");
        sidoMap.put("경상북도", "36.6308397,128.962578");
        sidoMap.put("광주광역시", "35.160032,126.851338");
        sidoMap.put("대구광역시", "35.87139,128.601763");
        sidoMap.put("대전광역시", "36.3504396,127.3849508");
        sidoMap.put("부산광역시", "35.179816,129.0750223");
        sidoMap.put("세종특별자치시", "36.4803512,127.2894325");
        sidoMap.put("울산광역시", "35.5394773,129.3112994");
        sidoMap.put("전라남도", "34.9007274,126.9571667");
        sidoMap.put("전라북도", "35.6910153,127.2368291");
        sidoMap.put("제주특별자치도", "33.4273366,126.5758344");
        sidoMap.put("충청남도", "36.6173379,126.8453965");
        sidoMap.put("충청북도", "36.7853718,127.6551404");
        sidoMap.put("인천광역시", "37.4559418,126.7051505");
        File file = new File(dataPath + "code.data");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
            String line = null;
            while((line = br.readLine())!=null){
                String[] tokens = line.split("\t");
                if(tokens[tokens.length - 1].equals("존재")){
                    String[] sidoTokens = tokens[1].split(" ");
                    if(sidoTokens.length==1){
                        SiDoCode siDoCode = SiDoCode.builder()
                                .code(tokens[0])
                                .addr(tokens[1])
                                .build();
                        String[] sidoAddrTokens = sidoMap.get(tokens[1]).split(",");
                        siDoCode.updateLocation(Double.parseDouble(sidoAddrTokens[0]), Double.parseDouble(sidoAddrTokens[1]));
                        siDoCodeRepository.save(siDoCode);
                    }else{
                        if(tokens[1].endsWith("특별시") || tokens[1].endsWith("도") || tokens[1].endsWith("광역시") || tokens[1].endsWith("특별자치시")){
                            SiDoCode siDoCode = SiDoCode.builder()
                                    .code(tokens[0])
                                    .addr(tokens[1])
                                    .build();
                            siDoCodeRepository.save(siDoCode);
                        }else if(tokens[1].endsWith("군") || tokens[1].endsWith("구") || tokens[1].endsWith("시")){
                            String siDoCodeId = tokens[0].substring(0, 2) + "00000000";
                            SiDoCode siDoCode = siDoCodeRepository.findById(siDoCodeId).get();
                            GuGunCode guGunCode = GuGunCode.builder()
                                    .code(tokens[0])
                                    .addr(tokens[1])
                                    .build();
                            guGunCode.setSiDoCode(siDoCode);
                            guGunCodeRepository.save(guGunCode);
                        }else{
                        /*
                        ToDo DongCode
                         */
                        }
                    }

                }
            }
            file = new File(dataPath + "location.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            for(int i = 0; i < rows; i++){
                XSSFRow row = sheet.getRow(i);
                if(row != null){
                    int cells=row.getPhysicalNumberOfCells();
                    String code = "";
                    String addr = "";
                    String lat = "";
                    String lng = "";
                    for(int cellNum = 0; cellNum < cells; cellNum++){
                        XSSFCell cell = row.getCell(cellNum);
                        String value = "";
                        if(cell==null){
                            continue;
                        }else{
                            switch (cell.getCellType()){
                                case FORMULA:
                                    value=cell.getCellFormula();
                                    break;
                                case NUMERIC:
                                    value=cell.getNumericCellValue()+"";
                                    break;
                                case STRING:
                                    value=cell.getStringCellValue()+"";
                                    break;
                                case BLANK:
                                    value=cell.getBooleanCellValue()+"";
                                    break;
                                case ERROR:
                                    value=cell.getErrorCellValue()+"";
                                    break;
                            }
                        }
                        if(cellNum == 0) code = value;
                        if(cellNum == 2) addr = value;
                        if(cellNum == 3) lng = value;
                        if(cellNum == 4) lat = value;
                    }
                    code = code.split("\\.")[0] + "00000";
                    if(addr.endsWith("특별자치시")){
                        SiDoCode siDoCode = siDoCodeRepository.findById(code).get();
                        siDoCode.updateLocation(Double.parseDouble(lat), Double.parseDouble(lng));
                        siDoCodeRepository.save(siDoCode);
                    }else{
                        GuGunCode guGunCode = guGunCodeRepository.findById(code).get();
                        guGunCode.updateLocation(Double.parseDouble(lat), Double.parseDouble(lng));
                        guGunCodeRepository.save(guGunCode);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
