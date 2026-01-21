<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<html>
<head>
    <title>Thêm tòa nhà</title>
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
          <li class="active">Chi tiết tòa nhà</li>
        </ul><!-- /.breadcrumb -->
      </div>

      <div class="page-content">

        <div class="page-header">
          <h1>
            Thông tin tòa nhà
          </h1>

        </div><!-- /.page-header -->

        <div class="row">
          <div class="col-xs-12">
          </div>
        </div>

        <!-- Bảng danh sách -->
        <div class="row" style="font-family: 'Times New Roman', Times, serif;">
          <form:form modelAttribute="buildingEdit" id="listForm" method="GET">
            <div class="col-xs-12">
            <form class = form-horizontal role = "form">
              <div class="form-group">
                <label class="col-xs-3">Tên tòa nhà</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="name"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Quận</label>
                <div class="col-xs-2">
                  <form:select class="form-control" path="district">
                            <form:option value="">--Chọn Quận--</form:option>
                            <form:options items="${districts}"/>
                  </form:select>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Phường</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="ward"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Đường</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="street"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Kết cấu</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="structure" name="structure" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Số tầng hầm</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="numberOfBasement"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Diện tích sàn</label>
                <div class="col-xs-9">
                  <form:input type="text" class="form-control" path="floorArea"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Hướng</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="direction"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Hạng</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="level"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Diện tích thuê</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="rentArea"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Giá thuê</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="rentPrice"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Mô tả giá</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="rentpricedescription" name="rentpricedescription" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Phí dịch vụ</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="servicefee" name="servicefee" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Phí ô tô</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="carfee" name="carfee" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Phí mô tô</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="motorbikefee" name="motorbikefee" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Phí ngoài giờ</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="overtimefee" name="overtimefee" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Tiền điện</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="electricityfee" name="electricityfee" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Đặt cọc</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="deposit" name="deposit" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Thanh toán</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="payment" name="payment" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Thời hạn thuê</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="renttime" name="renttime" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Thời gian trang trí</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="decorationtime" name="decorationtime" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Tên quản lý</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="managerName"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">SĐT quản lý</label>
                <div class="col-xs-9">
                  <form:input class="form-control" path="managerPhone"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Phí môi giới</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="brokeragefee" name="brokeragefee" value="">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Loại tòa nhà</label>
                <div class="col-xs-9">
                  <form:checkboxes items="${typeCodes}" path="typeCode"/>
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3">Ghi chú</label>
                <div class="col-xs-9">
                  <input class="form-control" type="text" id="note" name="note">
                </div>
              </div>
              <div class="form-group">
                <label class="col-xs-3"></label>
                <div class="col-xs-9">
                  <c:if test="${not empty buildingEdit.id}">
                      <button type="button" class="btn btn-primary" id="btnAddOrBuilding">Cập nhật tòa nhà</button>
                      <button type="button" class="btn btn-primary" id="btnCancel">Hủy tòa nhà</button>
                  </c:if>
                  <c:if test="${empty buildingEdit.id}">
                      <button type="button" class="btn btn-primary" id="btnAddOrBuilding">Thêm mới</button>
                      <button type="button" class="btn btn-primary" id="btnCancel">Hủy tòa nhà</button>
                  </c:if>
                </div>
              </div>
              <form:hidden path="id" id = "buildingId"/>
            </form>
          </div>
          </form:form>
        </div>

      </div><!-- /.page-content -->
    </div>
  </div><!-- /.main-content -->
</div><!-- /.main-container -->

<script>
  $('#btnAddOrBuilding').click(function(){
    var data = {};
    var typeCode = [];
    var formData = $('#listForm').serializeArray();
    $.each(formData, function(i,v){
      if(v.name!='typeCode'){
        data[""+v.name+""] = v.value;
      }
      else{
        typeCode.push(v.value);
      }
    });
    data['typeCode'] = typeCode;
    if(typeCode!=''){
        addOrUpdateBuilding(data);
    }
    else {
        window.location.href = "<c:url value="/admin/building-edit?typeCode=require"/>";
    }
  });

  function addOrUpdateBuilding(data){
  $.ajax({
      type: "POST",
      url: "${buildingAPI}",
      data: JSON.stringify(data),
      contentType:"application/json",
      dataType: "JSON",
      success: function (response){
        console.log("Success");
      },
      error: function(response){
        console.log("failed");
        console.log(response);
      }
    });
  }

  $('#btnCancel').click(function (){
      window.location.href = "/admin/building-list";
  });

  function assigmentBuilding(buildingId){
      $('#assignmentBuildingModal').modal();
      $('#buildingId').val();
    }

    $('#assignmentBuildingModal').click(function(e){
      e.preventDefault();
      var data = {};
      data['buildingId'] = $('#buildingId').val();
      var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
        return $(this).val();
      }).get();
      data['staffs'] = staffs;
      console.log('OK');
    })
</script>
</body>
</html>
