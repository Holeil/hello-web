<#import "parts/common.ftl" as common>

<@common.page>
<h2 class="mb-4">User: ${user.getUsername()}</h2>
<div class="user-profile">
    <div class="user-information">
        <div><h5>Email:</h5><span><#if user.getEmail()??>${user.getEmail()}<#else> none</#if></span></div>
        <div><h5>Information about user:</h5><span>Other information..................</span></div>
    </div>
    <div class="user-notes wrapper">
        <div class="content">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Название</th>
                <th scope="col">Номер специальности</th>
                <th scope="col">Действия</a></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Mark</td>
                <td>Otto</td>
                <td>
                    <a class="btn btn-outline-primary btn-sm" href="#" role="button">Edit</a>
                    <a class="btn btn-outline-primary btn-sm" href="#" role="button">Delete</a>
                </td>
            </tr>
            <tr>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>
                    <a class="btn btn-outline-primary btn-sm" href="#" role="button">Edit</a>
                    <a class="btn btn-outline-primary btn-sm" href="#" role="button">Delete</a>
                </td>
            </tr>
            <tr>
                <td>Larry</td>
                <td>the Bird</td>
                <td>
                    <a class="btn btn-outline-primary btn-sm" href="#" role="button">Edit</a>
                    <a class="btn btn-outline-primary btn-sm" href="#" role="button">Delete</a>
                </td>
            </tr>
            </tbody>
        </table>
        </div>
        <div class="footer">
            <a class="btn btn-primary" href="/user/${user.getId()}/addmessage" role="button">Create note</a>
        </div>
    </div>
</div>
</@common.page>