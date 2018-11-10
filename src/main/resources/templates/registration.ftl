<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>

<@common.page>
    Registration
    ${message?ifExists}
    <@login.login "/registration" />
</@common.page>