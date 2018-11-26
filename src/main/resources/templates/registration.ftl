<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
    <div class="ml-1">Регистрация</div>
    ${message?ifExists}
    <@login.login "/registration" true />
</@common.page>