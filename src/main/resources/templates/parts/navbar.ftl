<#import "login.ftl" as login>

<#include "security.ftl">

<nav class="navbar navbar-expand-lg <#if siteTheme??>${siteTheme.navbar}<#else>navbar-dark bg-dark</#if>">
    <a class="navbar-brand" href="/">Hello web</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
        <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">Список пользователей</span></a>
            </li>
        </#if>
        </ul>
        <#if user??>
            <div class="navbar-text mr-3">
                <a class="nav-link" href="/profile/${name}">${name}</span></a>
            </div>
            <#if isUser>
                <a href="/changetheme" class="btn <#if siteTheme??>${siteTheme.navbarButton}<#else>btn-primary</#if> mr-2">Переключить тему</a>
            </#if>
            <@login.logout />
        <#else >
            <a href="/login"><button type="submit" class="btn btn-primary">
                Войти</button></span>
            </a>
        </#if>
    </div>
</nav>