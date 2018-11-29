<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
<div class="container">
    <div>
        <div class="ml-1 mb-2"><h4>Регистрация</h4></div>
        ${message?ifExists}
        <@login.login "/registration" true />
    </div>
</div>
</@common.page>