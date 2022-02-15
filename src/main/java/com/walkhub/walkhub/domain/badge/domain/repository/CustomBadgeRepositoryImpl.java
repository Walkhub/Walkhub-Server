package com.walkhub.walkhub.domain.badge.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.MyBadgeVo;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.QDefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.QMyBadgeVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.walkhub.walkhub.domain.badge.domain.QBadge.badge;
import static com.walkhub.walkhub.domain.badge.domain.QBadgeCollection.badgeCollection;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
@Repository
public class CustomBadgeRepositoryImpl implements CustomBadgeRepository {

    private final JPAQueryFactory query;

    @Override
    public List<DefaultBadgeVO> findAllByBadgeCollectionsNotIn(Long userId) {
        return query
                .select(new QDefaultBadgeVO(
                        badge.id,
                        badge.name,
                        badge.imageUrl
                ))
                .from(badge)
                .leftJoin(badgeCollection)
                .on(badgeCollection.badge.eq(badge))
                .leftJoin(user)
                .on(badgeCollection.user.eq(user))
                .where(user.isNull().or(user.id.eq(userId).not()))
                .fetch();
    }

    @Override
    public List<MyBadgeVo> findAllByBadgeCollections(Long userId) {
        return query
                .select(new QMyBadgeVo(
                        badge.id,
                        badge.name,
                        badge.imageUrl,
                        user.id.eq(userId)
                ))
                .from(badge)
                .leftJoin(badgeCollection.badge, badge)
                .leftJoin(badgeCollection.user, user)
                .where(user.isNull().or(user.id.eq(userId)))
                .fetch();
    }

}
