GET:

$(document).ready(
    function () {
        $('#updateEmp').hide();
        $('#addNewEmp').hide();
        $('#addNewAttr').hide();
        $('#hiddenDetail').hide();
        $('#updateAttr').hide();
        $('#dialog-confirm').hide();
        $('#dialog-confirm2').hide();


        $("#showAttributes").hide();
        $('.four tr > *:nth-child(1)').hide();
        assignDataToTable();

        $('#addEmp').click(function () {

            assignSupToTable();
            $("#addNewEmp").slideToggle("fast", "swing");
        });

        $('#addAttr').click(function () {
            $("#addNewAttr").slideToggle("fast", "swing");

        });

        $('.cancel').click(function () {
            $('#updateEmp').hide(1000);
            $('#addEmp').show(1000);
            $('#showAttributes').hide(1000);
            $('#addNewAttr').hide(1000);
            $('#updateAttr').hide(1000);
            $("#upName").val("");
            $("#upSup").val("");
            $("#upNameAttr").val("");
            $("#upValue").val("");

        });

        //Delete Employee
        $('table tbody').on('click', '.delete', function (event) {
            event.preventDefault();
            var id = $(this).closest('tr').children('td:first').text();
            $('#showAttributes').hide(1000);

            $("#dialog-confirm").dialog({
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                buttons: {
                    "Delete all items": function () {
                        $(this).dialog("close");
                        $.ajax({
                            type: "DELETE",
                            url: "employees/" + id,
                            success: function (data) {
                                alertUsing("Employee Successfully Deleted", true);
                                assignDataToTable();
                            },
                            error: function (err) {
                                console.log(err);
                                alertUsing("Could not Delete Employee!! Something Went wrong", false);
                            }
                        });
                    },
                    Cancel: function () {
                        $(this).dialog("close");
                    }
                }
            });

        });

        //Save new Employee
        $("#saveEmp").click(function () {
            var hasNumber = /\d/;
            var classes = $('.input');
            if (($("#addName").val() === null) || ($.trim($("#addName").val()) === "")
                || ($("#supervisorId").val() === null)) {


                classes.addClass('wrong-input');
                setTimeout(function () {
                    classes.removeClass('wrong-input');
                }, (2000));
                alertUsing("Name or Supervisor Id cannot be Empty!", false);

            } else if (hasNumber.test($("#addName").val())) {
                classes.addClass('wrong-input');
                setTimeout(function () {
                    classes.removeClass('wrong-input');
                }, (2000));
                alertUsing("Name cannot contain Numbers!", false);
            }
            else {
                $('#addNewEmp').hide();
                var jsonVar = {
                    supervisor: {
                        emp_ID: $.trim($("#supervisorId").val())
                    },
                    emp_Name: $.trim($("#addName").val())
                };


                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/employees",
                    data: JSON.stringify(jsonVar),
                    contentType: "application/json",
                    success: function (data) {
                        alertUsing("Employee Successfully Added", true);
                        assignDataToTable();

                    },
                    error: function (err) {
                        console.log(err);
                        alertUsing("Could not Update Employee!! Something went wrong!", false);
                    }
                });
            }

        });

        //Show Update Employee Element
        var idEmp;
        var name;
        var supId;
        $('table tbody').on('click', '.edit', function (e) {

            assignSupToTable();
            $("#upName").val("");
            $("#upSup").val("");
            $('#updateEmp').hide();
            $('#addEmp').hide();
            $("#showAttributes").hide();
            $('#addNewAttr').hide();

            idEmp = $(this).closest('tr').children('td:first').text();
            name = $(this).closest('tr').children('td:nth-child(2)').text();
            supId = $(this).closest('tr').children('td:nth-child(4)').text();

            $("#upName").val(name);
            $("#upSup").val(supId);

            $("#updateEmp").toggle("slide", function () {
                $("#updateEmp").show();
                $("#addNewEmp").hide();
            })

        })

        //Update Employee
        $("#updateEmpB").click(function () {


            var hasNumber = /\d/;
            var classes = $('.input');
            if (($("#upName").val() === null) || ($.trim($("#upName").val()) === "")
                || ($("#supervisorId2").val() === null) || ($.trim($("#supervisorId2").val()) === "")) {


                classes.addClass('wrong-input');
                setTimeout(function () {
                    classes.removeClass('wrong-input');
                }, (2000));
                alertUsing("Name or Superviosr ID cannot be Empty!", false);

            } else if (hasNumber.test($("#upName").val())) {
                classes.addClass('wrong-input');
                setTimeout(function () {
                    classes.removeClass('wrong-input');
                }, (2000));
                alertUsing("Name cannot contain Numbers!", false);
            }
            else {
                $('#addNewEmp').hide();
                var jsonVar = {
                    supervisor: {
                        emp_ID: $.trim($("#supervisorId2").val())
                    },
                    emp_Name: $.trim($("#upName").val())

                };



                $.ajax({
                    type: "PUT",
                    data: JSON.stringify(jsonVar),
                    contentType: "application/json",
                    url: "http://localhost:8080/employees/" + idEmp,
                    success: function (data) {
                        $("#updateEmployee").hide();
                        alertUsing("Employee Successfully Updated!.", true);
                        $("#upName").val("");
                        $("#upSup").val("");
                        alertUsing("Employee Successfully Updated", true);
                        assignDataToTable();
                        $("#updateEmp").hide();
                        $('#addEmp').show();
                    },
                    error: function (err) {
                        console.log(err);
                        alertUsing("Cannot Update Employee!! Something went Wrong", false);
                    }

                });

            }

        });

        //Delete Attribute from Employee
        $('table tbody').on('click', '.deleteAttr', function (event) {
            event.preventDefault();
            var attrId = $(this).closest('tr').children('td:first').text();


            $("#dialog-confirm2").dialog({
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                buttons: {
                    "Delete all items": function () {
                        $(this).dialog("close");
                        $.ajax({
                            type: "DELETE",
                            url: "employees/" + idEmp + "/attributes/" + attrId,
                            success: function (data) {
                                alertUsing("Attribute deleted successfully", true);
                                assignAttributesToTable(idEmp);
                                $('#showAttributes').hide();
                            },
                            error: function (err) {
                                console.log(err);
                                alertUsing("Could not delete Attribute!! Something went wrong!", false);
                            }
                        });
                    },
                    Cancel: function () {
                        $(this).dialog("close");
                    }
                }
            });

        });


        //Save new Attribute
        $("#saveAttr").click(function () {


            var hasNumber = /\d/;
            var classes = $('.input');
            if (($("#addNametoAttr").val() === null) || ($.trim($("#addNametoAttr").val()) === "")
                || ($("#addValue").val() === null) || ($.trim($("#addValue").val()) === "")) {


                classes.addClass('wrong-input');
                setTimeout(function () {
                    classes.removeClass('wrong-input');
                }, (2000));
                alertUsing("Attribute Name or Value cannot be Empty!", false);

            } else if (hasNumber.test($("#addNametoAttr").val())) {
                classes.addClass('wrong-input');
                setTimeout(function () {
                    classes.removeClass('wrong-input');
                }, (2000));
                alertUsing("Name cannot contain Numbers!", false);
            }
            else {
                $('#addNewAttr').hide();
                $('#addNewEmp').hide();
                var jsonVar = {

                    attr_Name: $.trim($("#addNametoAttr").val()),
                    attr_Value: $.trim($("#addValue").val())
                };

                $.ajax({
                    type: "POST",
                    url: "employees/" + idEmp + "/attributes",
                    data: JSON.stringify(jsonVar),
                    contentType: "application/json",
                    success: function (data) {
                        alertUsing("New Attribute Successfully Added", true)
                        assignAttributesToTable(idEmp);
                    },
                    error: function (err) {
                        console.log(err);
                        alertUsing("Could not save new Attribute!! Something went wrong!", false);
                    }
                });
            }
        });

        //Show Update Attribute Element
        var updatedAttrId;
        var updatedAttributeName;
        var updatedAttributeValue;
        $('table tbody').on('click', '.editAttr', function (e) {

            $("#upNameAttr").val("");
            $("#upValue").val("");
            $('#updateAttr').show();
            $('#addEmp').hide();
            $("#showAttributes").hide();
            $('#addNewAttr').hide();

            updatedAttrId = $(this).closest('tr').children('td:first').text();
            updatedAttributeName = $(this).closest('tr').children('td:nth-child(2)').text();
            updatedAttributeValue = $(this).closest('tr').children('td:nth-child(3)').text();

            $("#upNameAttr").val(updatedAttributeName);
            $("#upValue").val(updatedAttributeValue);

        })

        //Update Attribute
        $("#updateAttrB").click(function () {

            var hasNumber = /\d/;
            var classes = $('.input');
            if (($("#upNameAttr").val() === null) || ($.trim($("#upNameAttr").val()) === "")
                || ($("#upValue").val() === null) || ($.trim($("#upValue").val()) === "")) {


                classes.addClass('wrong-input');
                setTimeout(function () {
                    classes.removeClass('wrong-input');
                }, (2000));
                alertUsing("Attribute Name or Value cannot be Empty!", false);

            } else if (hasNumber.test($("#upNameAttr").val())) {
                classes.addClass('wrong-input');
                setTimeout(function () {
                    classes.removeClass('wrong-input');
                }, (2000));
                alertUsing("Name cannot contain Numbers!", false);
            }
            else {
                $('#addNewAttr').hide();
                $('#addNewEmp').hide();
                var jsonVar = {

                    attr_Name: $.trim($("#upNameAttr").val()),
                    attr_Value: $.trim($("#upValue").val())

                };



                $.ajax({
                    type: "PUT",
                    data: JSON.stringify(jsonVar),
                    contentType: "application/json",
                    url: "employees/" + idEmp + "/attributes/" + updatedAttrId,
                    success: function (data) {
                        $("#updateAttr").hide();
                        $("#showAttributes").show();
                        alertUsing("Attribute Successfully Updated!.", true);
                        $("#upNameAttr").val("");
                        $("#upValue").val("");
                        assignAttributesToTable(idEmp);
                    },
                    error: function (err) {
                        console.log(err);
                        alertUsing("Could not update Attribute!! Something went wrong!", false);
                    }

                });
            }
        });


        $('.three tbody').on('click', '#seeAttr', function (e) {
            assignAttributesToTable(idEmp);
            $("#showAttributes").toggle("slow", function () {


            })
        });





        //  GET ALL EMPLOYEES
        function assignDataToTable() {
            $.ajax({

                type: "GET",
                url: "employees",
                success: function (result) {
                    $('#result').empty();
                    var employee = "";
                    $.each(result,
                        function (i) {
                            var suprevisorId;
                            if (result[i].supervisor) {
                               
                                suprevisorId = result[i].supervisor.emp_Name;
                            }
                            else {
                                suprevisorId = "None";
                            }
                            if (result[i].emp_Name != "None") {
                                var empId = "empId" + i;
                                employee +=
                                    '<tr class = "selectedEmployee">' +
                                    '<td class = "' + empId + '" name="empId">' + result[i].emp_ID + '</td>' +
                                    '<td > ' + result[i].emp_Name + '</td>' +
                                    '<td > ' + result[i].emp_DateOfHire + '</td>' +
                                    '<td > ' + suprevisorId + '</td>' +
                                    '<td ><input type="button" class = "btn edit" value="View / Edit"></td>' +
                                    '<td ><input type="button" class = "btn delete" value="Delete"></td></tr>';
                            }
                        });

                    $('#result').append(
                        employee);
                    console.log("Success: ", result);
                },
                error: function (e) {
                    $("#result").html("<h1>Error</h1><p>We are terribly sorry for the incovenience...</p>"+
                    "<p>Please try again later...</p>");
                    console.log("ERROR: ", e);
                }
            });
        }

        //  GET ALL ATTRIBUTES
        function assignAttributesToTable(employeeId) {
            $.ajax({

                type: "GET",
                url: "employees/" + employeeId + "/attributes",
                success: function (result) {
                    $('#resultAttr').empty();
                    var attribute = "";
                    $.each(result,
                        function (i) {
                            var attrId = i;
                            attribute +=
                                '<tr class = "selectedAttribute">' +
                                '<td class = "attrId" name="attrId">' + result[i].atrr_ID + '</td>' +
                                '<td > ' + result[i].attr_Name + '</td>' +
                                '<td > ' + result[i].attr_Value + '</td>' +
                                '<td ><input type="button" class = "btn editAttr" value="Edit"></td>' +
                                '<td ><input type="button" class = "btn deleteAttr" value="Delete"></td></tr>';
                        });

                    $('#resultAttr').append(
                        attribute);
                    console.log("Success: ", result);
                },
                error: function (e) {
                    $("#resultAttr").html("<h1>Error</h1><p>We are terribly sorry for the incovenience...</p>"+
                    "<p>Please try again later...</p>");
                    console.log("ERROR: ", e);
                }
            });
        }


        // Assign Supervisor IDs to New Employee select
        function assignSupToTable() {

            $('select[data-source]').html('');
            $('select[data-source]').each(function () {
                var $select = $(this);

                $.ajax({
                    url: $select.attr('data-source'),
                }).then(function (options) {
                    options.map(function (option) {
                        var $option = $('<option>');

                        $option
                            .val(option[$select.attr('data-valueKey')])
                            .text(option[$select.attr('data-displayKey')]);

                        $select.append($option);
                    });
                });
            });
        }

        $(function () {
            $("#supervisorId")
                .selectmenu()
                .selectmenu("menuWidget")
                .addClass("overflow");
            $("#supervisorId2")
                .selectmenu()
                .selectmenu("menuWidget")
                .addClass("overflow");
        });


        function alertUsing(text, flag) {

            var alert = $(".alert");

            if (flag) {
                alert.removeClass("alert-danger").addClass("alert-success");
            } else {
                alert.removeClass("alert-success").addClass("alert-danger");

            }

            alert.fadeIn(400);
            alert.css("display", "block");
            alert.text(text);
            setTimeout(function () {
                alert.fadeOut();
            }, 2000);

        }
    })
