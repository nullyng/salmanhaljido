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
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.io.*;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService{

    private String dataPath = "src/main/resources/data/";
    private final SiDoCodeRepository siDoCodeRepository;
    private final DongCodeRepository dongCodeRepository;
    private final GuGunCodeRepository guGunCodeRepository;

    @Override
    @Transactional
    @PostConstruct
    public void getData() {
        File file = new File(dataPath + "code.data");

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF8"));
            String line = null;
            while((line = br.readLine())!=null){
                String[] tokens = line.split("\t");
                if(tokens[tokens.length - 1].equals("존재")){
                    if(tokens[1].endsWith("시") || tokens[1].endsWith("도")){
                        SiDoCode siDoCode = SiDoCode.builder()
                                .code(tokens[0])
                                .addr(tokens[1])
                                .build();
                        siDoCodeRepository.save(siDoCode);
                    }else if(tokens[1].endsWith("군") || tokens[1].endsWith("구") || tokens[1].endsWith("시")){
                        SiDoCode siDoCode = siDoCodeRepository.findSiDoCodeByAddr(tokens[1].split(" ")[0]);
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
                        if(cellNum == 3) lat = value;
                        if(cellNum == 4) lng = value;
                    }
                    code = code.split("\\.")[0] + "00000";
                    if(addr.endsWith("시")){
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
