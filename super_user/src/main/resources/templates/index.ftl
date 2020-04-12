<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--font-family-->
        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&amp;subset=devanagari,latin-ext"
              rel="stylesheet">
        <!-- title of site -->
        <title>用户登录</title>

        <!-- For favicon png -->
        <link rel="shortcut icon" type="image/icon" href="../static/img/c66eae668f4cb7c8d5bf9a566f2154d.png"/>
        <!--font-awesome.min.css-->
        <link rel="stylesheet" href="../static/css/font-awesome.min.css">
        <!--animate.css-->
        <link rel="stylesheet" href="../static/css/animate.css">
        <!--bootstrap.min.css-->
        <link rel="stylesheet" href="../static/bootstrap/css/bootstrap.min.css">
        <!-- bootsnav -->
        <link rel="stylesheet" href="../static/css/bootsnav.css">
        <!--style.css-->
        <link rel="stylesheet" href="../static/css/style.css">
        <!--responsive.css-->
        <link rel="stylesheet" href="../static/css/responsive.css">
        <script src="../static/js/admin/ajax.js"></script>
        <style type="text/css">
            .container{
                background-image: url('../static/img/bg_index.jpg');
                background-repeat:repeat;
                background-attachment:fixed;
                background-position:center;
            }
        </style>

    </head>
    <body>



    <!-- 登录注册模块开始-->
    <section class="signin">
        <div class="container">
            <#-- 登录-->
            <div class="sign-content">
                <h2>欢迎登录超市库存销系统</h2>

                <div class="row">
                    <div class="col-sm-12">
                        <div class="signin-form">
                            <form action="signin.html">
                                <div class="form-group">
                                    <label for="signin_form">账号</label>
                                    <input type="email" class="form-control" id="login_uid" placeholder="enter your email here">
                                </div><!--/.form-group -->
                                <div class="form-group">
                                    <label for="signin_form">密码</label>
                                    <input type="password" class="form-control" id="login_password" placeholder="Password">
                                </div><!--/.form-group -->
                            </form><!--/form -->
                        </div><!--/.signin-form -->
                    </div><!--/.col -->
                </div><!--/.row -->

                <div class="row">
                    <div class="col-sm-12">
                        <div class="signin-footer">
                            <button type="button" class="btn signin_btn" onclick="login()">
                                登录
                            </button>
                            <p>
                                没有账号  ?
                                <button type="button" class="btn btn-link" data-toggle="modal" data-target=".signin_modal">注册</button>
                            </p>
                        </div><!--/.signin-footer -->
                    </div><!--/.col-->
                </div><!--/.row -->

            </div><!--/.sign-content -->
            <!--注册-->
            <!-- modal part start -->
            <div class="modal fade signin_modal" id="signin_modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="sign-content">

                            <div class="modal-header">
                                <h2>注册</h2>
                            </div><!--/.modal-header -->

                            <div class="modal-body">
                                <div class="signin-form">
                                    <div class=" ">
                                        <div class=" ">
                                            <form>
                                                <div class="form-col">
                                                    <div class="form-group">
                                                        <label for="signin_form">用户账号</label>
                                                        <input type="text" class="form-control" id="uid" placeholder="请输入账号">
                                                    </div><!--/.form-group -->
                                                </div><!--/.form-col -->
                                                <div class="form-col1">
                                                    <div class="form-group">
                                                        <label for="signin_form">用户名</label>
                                                        <input type="text" class="form-control" id="uname" placeholder="请输入用户名">
                                                    </div><!--/.form-group -->
                                                </div><!--/.form-col1 -->
                                                <div class="form-group">
                                                    <label for="signin_form">联系方式</label>
                                                    <input type="text" class="form-control" id="uphone" placeholder="请输入电话号码">
                                                </div><!--/.form-group -->
                                                <div class="form-group">
                                                    <label for="signin_form">密码</label>
                                                    <input type="password" class="form-control" id="upassword" placeholder="请输入密码">
                                                </div><!--/.form-group -->
                                                <div class="form-group">
                                                    <label for="signin_form">再次确认密码</label>
                                                    <input type="password" class="form-control" id="repeat" placeholder="再次输入密码">
                                                </div><!--/.form-group -->
                                            </form><!--/form -->
                                        </div><!--/.col -->
                                    </div><!--/.row -->

                                </div><!--/.signin-form -->

                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="signin-footer">
                                            <button type="button" class="btn signin_btn" onclick="register()">
                                                注册
                                            </button>
                                            <p>
                                                已经有账号了 ? &nbsp;&nbsp;
                                                <a href="">登录</a>
                                            </p>
                                        </div><!--/.signin-footer -->
                                    </div><!--/.col-->
                                </div><!--/.row -->
                            </div><!--/.modal-body -->

                        </div><!--/.sign-content -->
                    </div><!--/.modal-content -->
                </div><!--/.modal-dialog -->
            </div><!--/.modal -->
            <!-- modal part end -->
        </div><!--/.container -->

    </section><!--/.signin -->

    <!-- 登录注册模块结束 -->

    <footer class="footer-copyright">
        <div id="scroll-Top">
            <i class="fa fa-angle-double-up return-to-top" id="scroll-top" data-toggle="tooltip" data-placement="top" title="" data-original-title="Back to Top" aria-hidden="true"></i>
        </div><!--/.scroll-Top-->

    </footer>


    <script src="../static/jquery/jquery-3.3.1.min.js"></script>
    <!--modernizr.min.js-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js"></script>
    <!--bootstrap.min.js-->
    <script src="../static/bootstrap/js/bootstrap.min.js"></script>
    <!-- bootsnav js -->
    <script src="../static/js/bootsnav.js"></script>
    <!-- jquery.sticky.js -->
    <script src="../static/js/jquery.sticky.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
    <!--Custom JS-->
    <script src="../static/js/custom.js"></script>


    </body>
</html>