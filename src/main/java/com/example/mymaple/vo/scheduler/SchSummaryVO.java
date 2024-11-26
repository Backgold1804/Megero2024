package com.example.mymaple.vo.scheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SchSummaryVO {
    int user_id;
    int do_cnt;
    int all_cnt;
    int price;

    public SchSummaryVO(int user_id, int do_cnt, int all_cnt, int price) {
        this.user_id = user_id;
        this.do_cnt = do_cnt;
        this.all_cnt = all_cnt;
        this.price = price;
    }
}
