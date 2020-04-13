<t:messagesPanel />
<form:form modelAttribute="usersForm" action="update">

  <table>
    <tbody>
      <tr>
        <th width="100px">ユーザID</th>
        <td>
          ${f:h(usersForm.uid)}
          <form:hidden path="uid" />
        </td>
      </tr>
      <tr>
        <th>パスワード</th>
        <td>
          <form:input path="pass" autocomplete="off" />
          <form:errors path="pass" />
        </td>
      </tr>
      <tr>
        <th>氏名</th>
        <td>
          <form:input path="name" autocomplete="off" />
          <form:errors path="name" />
        </td>
      </tr>
      <tr>
        <th>メール</th>
        <td>
          <form:input path="mail" autocomplete="off" />
          <form:errors path="mail" />
        </td>
      </tr>
      <tr>
        <th>ステータス</th>
        <td>
          <form:input path="status" autocomplete="off" />
          <form:errors path="status" />
        </td>
      </tr>
      <tr>
        <th>コメント</th>
        <td>
          <form:textarea path="comment" rows="5" cols="30" autocomplete="off" />
          <form:errors path="comment" />
        </td>
      </tr>
    </tbody>
  </table>
    <input type="submit" name="form" value="戻る" form="detail"/>
  <input type="submit" name="confirm" value="確認" />
</form:form>
<form:form modelAttribute="usersForm" action="detail"
cssClass="inline" id="detail">
<form:hidden path="uid" value="${f:h(users.uid)}"/>
</form:form>