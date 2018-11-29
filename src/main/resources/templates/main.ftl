<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as p>

<@common.page>
<div class="d-flex">
    <div class="container">
        <#list page.content as message>
        <div class="card ${siteTheme.card} center-block mb-5">
            <a href="/message/${message.id}" class="card-header ${siteTheme.cardHeader}" style="color: black;">
                <div>
                    <h5><span class="font-weight-bold">${language.title}: </span>${message.title}</h5>
                </div>
                <div>
                    <h6><span class="font-weight-bold">${language.specialty}: </span>${message.specialty}</h6>
                </div>
            </a>
            <div class="card-body ${siteTheme.cardBody}" style="overflow: hidden; height: 200px;">
                <p class="card-text">${converter.markdownToHtml(message.text)}</p>
            </div>
            <div class="card-footer d-flex bd-highlight ${siteTheme.cardFooter}">
                <div class="p-2 flex-grow-1 bd-highlight">
                    <span class="mr-1">${language.author}: </span>
                    <a href="/profile/${message.author.username}">${message.author.username}</a>
                    <span class="ml-3">${message.date}</span>
                </div>
                <div class="p-2 bd-highlight">
                    <i class="far fa-star"></i>
                    <i class="far fa-star"></i>
                    <i class="far fa-star"></i>
                    <i class="far fa-star"></i>
                    <i class="far fa-star"></i>
                    0
                </div>
            </div>
        </div>
        </#list>
        <@p.pager url page />
    </div>
    <div class="col-sm-4">
        <div class="mb-2">
            #ТутТЭГИ
        </div>
        <div>
            <h4 class="font-weight-bold">Топ 5 конспектов</h4>
        </div>
    </div>
</div>
</@common.page>