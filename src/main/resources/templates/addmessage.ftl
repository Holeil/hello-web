<#import "parts/common.ftl" as common>

<@common.page>
    <form action="/user/${user.id}/addmessage" method="post" class="mt-5">
        <h2>New note</h2>
        <div class="row mb-5">
            <div class="col">
                <input type="text" name="title" class="form-control" placeholder="Title">
            </div>
            <div class="col">
                <input type="text" name="specialty" class="form-control" placeholder="Specialty number">
            </div>
        </div>
        <div class="row mb-5">
            <div class="col">
                <textarea class="form-control" name="text" placeholder="Text"></textarea>
            </div>
        </div>
        <div class="row mb-5">
            <div class="col">
                <input type="text" name="tag" class="form-control" placeholder="Tags">
            </div>
            <div class="col footer">
                <button type="submit" class="btn btn-primary">Create</button>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</@common.page>