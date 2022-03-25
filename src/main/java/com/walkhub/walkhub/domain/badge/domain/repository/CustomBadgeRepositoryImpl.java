package com.walkhub.walkhub.domain.badge.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.DefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.MyBadgeVo;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.QDefaultBadgeVO;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.QMyBadgeVo;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.walkhub.walkhub.domain.badge.domain.QBadge.badge;
import static com.walkhub.walkhub.domain.badge.domain.QBadgeCollection.badgeCollection;
import static com.walkhub.walkhub.domain.user.domain.QUser.user;

@RequiredArgsConstructor
public class CustomBadgeRepositoryImpl implements CustomBadgeRepository {

    private final JPAQueryFactory query;

    @Override
    public List<DefaultBadgeVO> findAllByBadgeCollectionsNotIn(Long userId) {
        return query
                .select(new QDefaultBadgeVO(
                        badge.id,
                        badge.name,
                        badge.imageUrl,
                        badge.code
                ))
                .from(badge)
                .leftJoin(badgeCollection)
                .on(badgeCollection.badge.eq(badge))
                .leftJoin(user)
                .on(badgeCollection.user.eq(user),
                        user.id.eq(userId))
                .where(user.id.isNull())
                .fetch();
    }

    @Override
    public List<MyBadgeVo> findAllByBadgeCollections(Long userId) {
        return query
                .select(new QMyBadgeVo(
                        badge.id,
                        badge.name,
                        badge.imageUrl,
                        badge.code,
                        badge.unlockCondition,
                        user.id.max().eq(userId)
                ))
                .from(badge)
                .leftJoin(badge.badgeCollections, badgeCollection)
                .leftJoin(badgeCollection.user, user).on(user.id.eq(userId))
                .groupBy(badge.id)
                .fetch();
    }

}
