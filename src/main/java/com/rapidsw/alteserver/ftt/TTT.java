package com.rapidsw.alteserver.ftt;

import com.rapidsw.alteserver.Literals;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.hwp.HwpV5Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

@Controller
@RequestMapping("/ttt")
public class TTT {

    @PostMapping("/hwp")
    public ResponseEntity<String> hwp(@RequestParam MultipartFile hwp){
        if (hwp.isEmpty()) return new ResponseEntity<>("file is empty!", HttpStatus.BAD_REQUEST);
        File file;
        try {
            file = new File(Literals.FILE_PATH);
            boolean m;
            if (!file.exists()) {
                m = file.mkdir();
                if (!m) return new ResponseEntity<>("couldn't make file", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            System.out.println(hwp);
            System.out.println(file);
            file = new File(file, hwp.getOriginalFilename());
            hwp.transferTo(file);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            System.out.println(file);
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            ParseContext parseContext = new ParseContext();

            HwpV5Parser parser = new HwpV5Parser();
            System.out.println("before parsing");
            parser.parse(fis,handler,metadata,parseContext);
            System.out.println("after parsing");
            System.out.println(handler);

            return new ResponseEntity<>(handler.toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
