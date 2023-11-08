package com.dw.fileio.service;

import com.dw.fileio.model.Game;
import com.dw.fileio.repository.GameRepository;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameIOService {
    @Autowired
    private GameRepository gameRepository;

    // 파일위치는 src/main/resources
    @Value("classpath:gameshopjson.json")
    private Resource jsonFile;

    @PostConstruct
    public void initialize() {
        processJsonData(jsonFile);
    }

    @Transactional
    public void processJsonData(Resource fileResource) {
        try {
            InputStream inputStream = fileResource.getInputStream();
            
            if (gameRepository.count() == 0) {
            	List<Game> dataList = readJsonData(inputStream);
                gameRepository.saveAll(dataList);
            } else {
                System.out.println("테이블에 데이터가 있을 경우 저장하지 않습니다");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Game> readJsonData(InputStream inputStream) throws IOException {
        List<Game> dataList = new ArrayList();

        try (JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream))) {
            jsonReader.beginArray();

            while (jsonReader.hasNext()) {
                Game data = new Gson().fromJson(jsonReader, Game.class);
                dataList.add(data);
            }

            jsonReader.endArray();
        }

        return dataList;
    }

}

