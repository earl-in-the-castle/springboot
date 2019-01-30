package com.example.firstspringboot.reposity;

import com.example.firstspringboot.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//其中第二个参数为Id的类型
public interface GirlRepository extends JpaRepository<Girl,Integer> {
    public List<Girl> findByAge(Integer age);
}
