package com.walkhub.walkhub.domain.badge.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.ClaimBadgeVO;
import com.walkhub.walkhub.domain.badge.domain.repository.vo.QClaimBadgeVO;
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
    public List<ClaimBadgeVO> findAllByBadgeCollectionsNotIn(Long userId) {
        return query
                .select(new QClaimBadgeVO(
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

}
