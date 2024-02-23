package kernel.jdon.moduleapi.domain.coffeechat.core;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import kernel.jdon.coffeechat.domain.CoffeeChat;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedSourcePolicy = ReportingPolicy.WARN
)
public interface CoffeeChatInfoMapper {

    @Mapping(source = "coffeeChat.id", target = "coffeeChatId")
    @Mapping(expression = "java(coffeeChat.getMember().getId())", target = "hostId")
    @Mapping(expression = "java(coffeeChat.getMember().getNickname())", target = "nickname")
    @Mapping(expression = "java(coffeeChat.getMember().getJobCategory().getName())", target = "job")
    @Mapping(expression = "java(coffeeChat.getCoffeeChatStatus().getActiveStatus())", target = "status")
    CoffeeChatInfo.FindResponse of(CoffeeChat coffeeChat);

    @Mapping(source = "coffeeChat.id", target = "coffeeChatId")
    @Mapping(source = "coffeeChat.title", target = "title")
    @Mapping(source = "coffeeChat.meetDate", target = "meetDate")
    @Mapping(source = "coffeeChat.createdDate", target = "createdDate")
    @Mapping(expression = "java(coffeeChat.getIsDeleted())", target = "isDeleted")
    @Mapping(expression = "java(coffeeChat.getMember().getNickname())", target = "nickname")
    @Mapping(expression = "java(coffeeChat.getMember().getJobCategory().getName())", target = "job")
    @Mapping(expression = "java(coffeeChat.getCoffeeChatStatus().getActiveStatus())", target = "activeStatus")
    CoffeeChatInfo.FindListResponse listOf(CoffeeChat coffeeChat);
}
