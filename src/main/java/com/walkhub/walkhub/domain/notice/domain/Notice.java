package com.walkhub.walkhub.domain.notice.domain;

import com.walkhub.walkhub.domain.notice.domain.type.Scope;
import com.walkhub.walkhub.domain.user.domain.User;
import com.walkhub.walkhub.global.entity.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notice extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200, nullable = false)
	private String title;

	@Column(columnDefinition = "char(3)", nullable = false)
	@Enumerated(EnumType.STRING)
	private Scope scope;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Builder
	public Notice(String title, Scope scope, String content,
		User user) {
		this.title = title;
		this.scope = scope;
		this.content = content;
		this.user = user;
	}
}
