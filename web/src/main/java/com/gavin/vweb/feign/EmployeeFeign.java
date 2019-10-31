package com.gavin.vweb.feign;

import com.gavin.vweb.dto.Employee;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Component
@FeignClient(name = "v-service")
public interface EmployeeFeign {

    /**
     * @description  通过id查用户信息
     * @author Gavin
     * @date 2019-10-31 15:10

     * @return com.gavin.vweb.dto.Employee
     * @throws
     * @since
    */
    @GetMapping(value = "/service/selectById")
    Employee selectById(@RequestParam(value = "id") Integer id);


    /**
     * @description  增加员工或修改员工信息, 两个操作在一起
     * @author Gavin
     * @date 2019-10-31 15:11

     * @return boolean
     * @throws
     * @since
    */
    @PostMapping("/service/modifyEmployeeInfo")
    boolean modifyEmployeeInfo(@RequestBody Employee employee);


    @PostMapping(value = "/service/deleteEmployee")
    boolean deleteEmployee(@RequestParam(value = "ids[]") Integer... ids);

    @GetMapping(value = "/service/selectByName")
    List<Employee> selectByName(@RequestParam(value = "name") String name);

    @GetMapping(value = "/service/selectByNameWithPage")
    public HashMap<Integer, List<Employee>> selectByNameWithPage(@RequestParam(value = "name") String name,
                                                                 @RequestParam(value = "start") int start,
                                                                 @RequestParam(value = "offset") int offset);

}
