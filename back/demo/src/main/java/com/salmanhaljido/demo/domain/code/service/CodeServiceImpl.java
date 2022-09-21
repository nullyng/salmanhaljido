package com.salmanhaljido.demo.domain.code.service;

import com.salmanhaljido.demo.domain.code.entity.DongCode;가
import com.salmanhaljido.demo.domain.code.entity.GuGunCode;
import com.salmanhaljido.demo.domain.code.entity.SiDoCode;
import com.salmanhaljido.demo.domain.code.repository.DongCodeRepository;
import com.salmanhaljido.demo.domain.code.repository.GuGunCodeRepository;
import com.salmanhaljido.demo.domain.code.repository.SiDoCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void getData() {
        File file = new File(dataPath + "code.data");

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
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
                    }else if(tokens[1].endsWith("군") || tokens[1].endsWith("구")){
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
