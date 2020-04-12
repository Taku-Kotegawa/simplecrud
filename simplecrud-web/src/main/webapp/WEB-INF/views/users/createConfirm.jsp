<t:messagesPanel />

<div>
以下の内容でユーザを登録します。
</div>

<form:form modelAttribute="usersForm" action="create">

  <table>
    <tbody>
      <tr>
        <th width="100px">ユーザID</th>
        <td>
          ${f:h(users.uid)}
          <form:hidden path="uid" />
        </td>
      </tr>
      <tr>
        <th>パスワード</th>
        <td>
          ${f:h(users.pass)}
          <form:hidden path="pass" />
        </td>
      </tr>
      <tr>
        <th>氏名</th>
        <td>
          ${f:h(users.name)}
          <form:hidden path="name" />
        </td>
      </tr>
      <tr>
        <th>メール</th>
        <td>
          ${f:h(users.mail)}
          <form:hidden path="mail" />
        </td>
      </tr>
      <tr>
        <th>コメント</th>
        <td>
          ${f:h(users.comment)}
          <form:hidden path="comment" />
        </td>
      </tr>
    </tbody>
  </table>

  <input type="submit" name="redo" value="Back" onclick="dis(this);" />
  <input type="submit" value="Save" onclick="dis(this);"/>
</form:form>

<script>
function dis(e) {
setTimeout(function(){e.disabled=true;},0);return true;
}
</script>
