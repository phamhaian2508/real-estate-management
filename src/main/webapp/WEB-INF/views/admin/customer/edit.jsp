<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customer"/>
<html>
<head>
    <title>Thêm khách hàng</title>
</head>
<body>
<div class="main-content" id="main-container">
  <div class="main-content">
    <div class="main-content-inner">
      <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
          try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
        </script>

        <ul class="breadcrumb">
          <li>
            <i class="ace-icon fa fa-home home-icon"></i>
            <a href="#">Trang chủ</a>
          </li>
          <li class="active">Thêm khách hàng</li>
        </ul><!-- /.breadcrumb -->
      </div>

      <div class="page-content">

        <div class="page-header">
          <h1>
            Thông tin khách hàng
          </h1>

        </div><!-- /.page-header -->

        <div class="row">
          <div class="col-xs-12">
          </div>
        </div>

        <!-- Bảng danh sách -->
        <div class="row" style="font-family: 'Times New Roman', Times, serif;">
          <form:form modelAttribute="customerEdit" id="listForm" method="GET">
            <div class="col-xs-12">
            <form class = form-horizontal role = "form">
              <div class="form-group">
                <label class="col-xs-3">Tên khách hàng</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="fullname"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Số điện thoại</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="phone"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Email</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="email"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Tên công ty</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="companyname"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Nhu cầu</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="demand"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Tình trạng</label>
                <div class="col-xs-9">
                  <form:input type="text" class="form-control" path="status"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3"></label>
                <div class="col-xs-9">
                  <c:if test="${not empty customerEdit.id}">
                      <button type="button" class="btn btn-primary" id="btnAddOrCustomer">Chỉnh sửa</button>
                      <button type="button" class="btn btn-primary" id="btnCancel">Hủy</button>
                  </c:if>
                  <c:if test="${empty customerEdit.id}">
                      <button type="button" class="btn btn-primary" id="btnAddOrCustomer">Thêm khách hàng</button>
                      <button type="button" class="btn btn-primary" id="btnCancel">Hủy</button>
                  </c:if>
                </div>
              </div>
              <form:hidden path="id" id = "customerId"/>
            </form>
          </div>
          </form:form>
        </div>
        <c:forEach var="it" items="${transactionList}">
        <div class = "col-xs-12">
        <div class = "col-xs-12">
                <h3 class = "header smaller lighter blue">${it.value}</h3>
                <button class="btn btn-xs btn-primary" title="Thêm" onclick="TransactionType('${it.key}',${customerEdit.id})">
                    <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                </button>
        </div>
<%--        Bảng--%>
            <div class = "col-xs-12">
            <table id="tableList" class="table table-striped table-bordered table-hover">
              <thead>
              <tr>
                <th class="center">
                  <label class="pos-rel">
                    <input type="checkbox" name="checkList" value="" class="ace">
                    <span class="lbl"></span>
                  </label>
                </th>
                <th>Ngày tạo</th>
                <th>Người tạo</th>
                <th>Ngày sửa</th>
                <th>Người sửa</th>
                <th>Chi tiết giao dịch</th>
                <th>Thao tác</th>
              </tr>
              </thead>

              <tbody>
                  <c:forEach var="item" items="${it.key == 'CSKH' ? CSKHList : DDXList}">
                      <tr>
                      <td class="center">
                        <label class="pos-rel">
                          <input type="checkbox" class="ace" name="checkList" value="${item.id}">
                          <span class="lbl"></span>
                        </label>
                      </td>

                      <td>${item.createdDate}</td>
                      <td>${item.createdBy}</td>
                      <td>${item.modifiedDate}</td>
                      <td>${item.modifiedBy}</td>
                      <td>${item.note}</td>
                      <td>
                          <button class="btn btn-xs btn-info" title="Sửa" onclick="transaction(${item.id})">
                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                          </button>
                      </td>
                    </tr>
                  </c:forEach>
              </tbody>
            </table>
            </div>
            </div>
           </c:forEach>
      </div><!-- /.page-content -->
    </div>
  </div><!-- /.main-content -->
</div><!-- /.main-container -->
<div class="modal fade" id="transactionModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
<div class="modal-dialog">

  <!-- Modal content-->
  <div class="modal-content">
    <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal">×</button>
    <h4 class="modal-title">Nhập giao dịch</h4>
    </div>

    <div class="modal-body">
        <div class="form-group has-success" id="formSerial">
            <label for="transactionNote" class="col-xs-12 col-sm-3 control-label no-padding-right">Chi tiết giao dịch</label>
            <div class="col-xs-12 col-sm-9">
                <span class="block input-icon input-icon-right">
                    <input type="text" id="transactionNote" class="width-100">
<%--                    <form:input id="transactionDetail" path="note" cssClass="width-100"/>--%>
                </span>
            </div>
        </div>
        <input type="hidden" id="customerId">
        <input type="hidden" id="code">
        <input type="hidden" id="id">
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" id="btnassignmentCustomer">Thêm giao dịch</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
    </div>
  </div>

</div>
</div>
<script>
   function transaction(id){
        $('#transactionModal').modal();
        $('#id').val(id);
        loadTransactionDetail(id);
   }
  function TransactionType(code,customerId){
    $('#transactionModal').modal();
    $('#customerId').val(customerId);
    $('#code').val(code);
  }
  function loadTransactionDetail(id) {
    $.ajax({
        url: "/api/customer/" + id + "/details",
        type: "GET",
        dataType: "json",
        success: function (res) {
            let row = '';
            $.each(res.data, function (index, item) {
                row += '<input type="text"'+ 'id="transactionNote" class="width-100" value="' + item + '" />';
            });

            $("#formSerial .input-icon").html(row);

            $("#transactionTypeModal").modal();
        },
        error: function (res) {
            window.alert("Fail");
        }
    });
}

  $('#btnassignmentCustomer').click(function(e){
      e.preventDefault(); // Tránh load nham trang
      var data = {};
      var id = $('#id').val();
      data['id'] = id;
      data['note'] = $('#transactionNote').val();
      data['customerId'] = $('#customerId').val();
      data['code'] = $('#code').val();
      if(id !==""){
        EditTransaction(data);
      }
      AddTransaction(data);
    });
    function AddTransaction(data){
        $.ajax({
        url: "${customerAPI}/"+'transaction',
        type: "POST",
        data: JSON.stringify(data),
        contentType:"application/json",
        dataType: "json",
        success: function (response){
            location.reload();
        },
        error: function(response){
          console.info("Giao không thành công!");
          location.reload();
          console.log(response);
        }
      });
    }
    function EditTransaction(data){
        $.ajax({
        url: "${customerAPI}/"+'transactionEdit',
        type: "POST",
        data: JSON.stringify(data),
        contentType:"application/json",
        dataType: "json",
        success: function (response){
          console.info("Success");
        },
        error: function(response){
          console.info("Giao không thành công!");
          location.reload();
          console.log(response);
        }
      });
    }
  $('#btnAddOrCustomer').click(function(){
    var data = {};
    var formData = $('#listForm').serializeArray(); //chuyển sang (name,value)
    //Duyệt dl qua các name
    $.each(formData, function(i,v){
      data[""+v.name+""] = v.value;
    });
    if(data["name"]!='' && data["customerPhone"]!=''){
        addOrUpdateCustomer(data);
    }
    else {
        window.location.href = "<c:url value="/admin/customer-edit?typeCode=require"/>";
    }
  });

  function addOrUpdateCustomer(data){
  $.ajax({
      type: "POST",
      url: "${customerAPI}",
      data: JSON.stringify(data),
      contentType:"application/json",
      // dataType: "JSON",
      success: function (){
          window.alert("Success");
      },
      error: function(){
        window.alert("Fail");
      }
    });
  }

  $('#btnCancel').click(function (){
      window.location.href = "/admin/customer-list";
  });
</script>
</body>
</html>
