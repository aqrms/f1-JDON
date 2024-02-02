package kernel.jdon.coffeechat.dto.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import kernel.jdon.coffeechat.domain.CoffeeChat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateCoffeeChatRequest {

	private String title;
	private String content;
	private Long totalRecruitCount;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private LocalDateTime meetDate;
	private String openChatUrl;

	public CoffeeChat toEntity() {
		return CoffeeChat.builder()
			.title(this.title)
			.content(this.title)
			.totalRecruitCount(this.totalRecruitCount)
			.meetDate(this.meetDate)
			.openChatUrl(this.openChatUrl)
			.build();
	}
}

