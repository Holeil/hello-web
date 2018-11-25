<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as p>

<@common.page>
    <@p.pager url page />
    <#list page.content as message>
    <div class="card center-block mb-5">
        <a href="/message/${message.id}" class="card-header">
            <h5>${message.title}</h5><h6>${message.specialty}</h6>
        </a>
        <div class="card-body">
            <p class="card-text">${message.text}</p>
        </div>
        <div class="card-footer d-flex bd-highlight">
            <div class="p-2 flex-grow-1 bd-highlight">
                <a href="/profile/${message.author.username}">${message.author.username}</a>
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
</@common.page>