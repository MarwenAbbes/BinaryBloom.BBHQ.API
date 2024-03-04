package com.binarybloom.userms.controllers;

import com.binarybloom.userms.entities.CompanyInfo;
import com.binarybloom.userms.repositories.CompanyInfoRepository;
import com.binarybloom.userms.services.LoggerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/companyInfo")
public class CompanyInfoController {
    private LoggerService logger;
    private CompanyInfoRepository companyInfoRepository;

    public CompanyInfoController(LoggerService logger, CompanyInfoRepository companyInfoRepository) {
        this.logger = logger;
        this.companyInfoRepository = companyInfoRepository;
    }

    @GetMapping
    private ResponseEntity<CompanyInfo> get() {
        try {
            List<CompanyInfo> _companyInfo = companyInfoRepository.findAll();
            if (!_companyInfo.isEmpty()) {
                CompanyInfo companyInfo = _companyInfo.get(0);
                return new ResponseEntity<>(companyInfo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    private ResponseEntity<Boolean> insert(@RequestBody CompanyInfo item) {
        try {
            List<CompanyInfo> _companyInfo = companyInfoRepository.findAll();
            if ((long) _companyInfo.size() == 0) {
                companyInfoRepository.save(item);
                return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
            } else {
                logger.error("CompanyInfo already exist");
                return new ResponseEntity<>(Boolean.FALSE, HttpStatus.METHOD_NOT_ALLOWED);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    private ResponseEntity<Boolean> update(@RequestBody CompanyInfo item) {
        try {

            companyInfoRepository.save(item);
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(Boolean.FALSE, HttpStatus.NOT_FOUND);
        }
    }
}
