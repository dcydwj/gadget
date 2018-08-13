package rpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: congying.deng@ymm56.com
 * @Time: 2018-07-21 19:58
 * @Version: 1.0
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 6204167431229575373L;

    private Integer id;

    private String name;



    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private List<User> childs;


    public void addChild(User child) {
        if (null  == childs) {
            childs = new ArrayList<>();
        }
        childs.add(child);
    }


}
