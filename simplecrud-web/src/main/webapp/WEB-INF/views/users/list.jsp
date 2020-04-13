<!-- ユーザ覧表-->
<h2>ユーザ一覧</h2>

<div>
  <form:form action="create" cssClass="inline">
    <input type="submit" name="form" value="新規登録" />
  </form:form>
</div>
<table>
  <thead>
    <tr>
      <th>#</th>
      <th>操作</th>
      <th>ユーザID</th>
      <th>氏名</th>
      <th>パスワード</th>
      <th>メール</th>
      <th>ステータス</th>
      <th>作成日時</th>
      <th>最終更新</th>
      <th>コメント</th>
    </tr>
  </thead>
  <tbody>

    <c:forEach var="users" items="${usersList}" varStatus="status">
      <tr>
        <td>${status.count}</td>
        <td>
          <form:form modelAttribute="usersForm" action="detail"
            cssClass="inline">
            <form:hidden path="uid" value="${f:h(users.uid)}" />
            <input type="submit" name="form" value="参照" />
          </form:form>
        </td>
        <td>${f:h(users.uid)}</td>
        <td>${f:h(users.name)}</td>
        <td>${f:h(users.pass)}</td>
        <td>${f:h(users.mail)}</td>
        <td>${f:h(users.status)}</td>
        <td>${f:h(users.createdAtDisp)}</td>
        <td>${f:h(users.changedAtDisp)}</td>
        <td>${f:h(users.comment)}</td>
      </tr>
    </c:forEach>

  </tbody>
</table>
