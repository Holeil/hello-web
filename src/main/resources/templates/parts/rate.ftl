<#include "security.ftl">

<#if messageRateRepo.findByMessageIdAndUserId(message.id, userId)[0]??>
    <#assign
        myMessageRate = messageRateRepo.findByMessageIdAndUserId(message.id, userId)

        meRated = myMessageRate[0]??

        mySetRate = myMessageRate[0].getRate()

        messageRate = messageRateService.countRate(message)

    >
<#else >
    <#assign
        mySetRate = 0

        meRated = false

        messageRate = messageRateService.countRate(message)
    >
</#if>

<form action="/message/${message.id}/rates" class="p-2 bd-highlight" method="post">
    <label class="cursor-pointer">
        <#if meRated && (mySetRate >= 1) >
            <i class="fas fa-star"></i>
        <#else>
            <i class="far fa-star"></i>
        </#if>
        <#if user?? && isUser><button type="submit" name="rate" value="1" class="d-none"></button></#if>
    </label>
    <label class="cursor-pointer">
        <#if meRated && (mySetRate >= 2) >
            <i class="fas fa-star"></i>
        <#else>
            <i class="far fa-star"></i>
        </#if>
        <#if user?? && isUser><button type="submit" name="rate" value="2" class="d-none"></button></#if>
    </label>
    <label class="cursor-pointer">
        <#if meRated  && (mySetRate >= 3) >
            <i class="fas fa-star"></i>
        <#else>
            <i class="far fa-star"></i>
        </#if>
        <#if user?? && isUser><button type="submit" name="rate" value="3" class="d-none"></button></#if>
    </label>
    <label class="cursor-pointer">
        <#if meRated  && (mySetRate >= 4) >
            <i class="fas fa-star"></i>
        <#else>
            <i class="far fa-star"></i>
        </#if>
        <#if user?? && isUser><button type="submit" name="rate" value="4" class="d-none"></button></#if>
    </label>
    <label class="cursor-pointer">
        <#if meRated && (mySetRate >= 5) >
            <i class="fas fa-star"></i>
        <#else>
            <i class="far fa-star"></i>
        </#if>
        <#if user?? && isUser><button type="submit" name="rate" value="5" class="d-none"></button></#if>
    </label>
    ${messageRate}
    <#if user?? && isUser><input type="hidden" name="_csrf" value="${_csrf.token}" /></#if>
</form>