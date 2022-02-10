package com.walkhub.walkhub.global.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class QuerydslUtil {
    public BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }
}
