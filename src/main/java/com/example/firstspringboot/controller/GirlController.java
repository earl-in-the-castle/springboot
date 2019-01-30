package com.example.firstspringboot.controller;

import com.example.firstspringboot.domain.Girl;
import com.example.firstspringboot.domain.Result;
import com.example.firstspringboot.reposity.GirlRepository;
import com.example.firstspringboot.service.GirlService;
import com.example.firstspringboot.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRep;

    @Autowired
    private GirlService girlService;


    /**
     * 查询所有女生列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> getGirlList(){
        logger.info("getGirlList");
        return girlRep.findAll();
    }

    /**
     * 添加一个女生
     * @param girl
     * @return
     */
    @PostMapping(value = "/girls")
    public Result<Girl> addGirl(@Validated Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setAge(girl.getAge());
        girl.setCupSize(girl.getCupSize());
        return ResultUtil.success(girlRep.save(girl));
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @GetMapping(value="/girls/{id}")
    public Girl findById(@PathVariable("id") Integer id){
        return girlRep.findOne(id);
    }


    /**
     * 根据id查找
     * @param age
     * @return
     */
    @GetMapping(value="/girls/age/{age}")
    public List<Girl> findByAge(@PathVariable("age") Integer age){
        return girlRep.findByAge(age);

    }


    /**
     * 修改
     * @param id
     * @param cupSize
     * @param age
     * @return
     */
    @PutMapping(value="/girls/{id}")
    public Girl findById(@PathVariable("id") Integer id,
                         @RequestParam("cupSize")String cupSize,
                         @RequestParam("age")Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRep.save(girl);

    }


    /**
     * 删除
     * @param id
     */
    @DeleteMapping(value="/girls/{id}")
    public void delete(@PathVariable("id") Integer id){

        girlRep.delete(id);

    }


    @RequestMapping(value="/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception{

        girlService.getAge(id);

    }

}

