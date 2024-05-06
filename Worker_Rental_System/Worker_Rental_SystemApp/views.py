from datetime import datetime

from django.contrib import auth
from django.contrib.auth.decorators import login_required
from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse
from django.shortcuts import render, redirect
from django.db import connection
import json
from django.http import JsonResponse


# Create your views here.
from Worker_Rental_SystemApp.models import *


def login(request):
    return render(request, "login_index.html")


def logout(request):
    auth.logout(request)
    return render(request, "login_index.html")


def login_fun(request):
    try:
        username = request.POST['Username']
        password = request.POST['Password']
        login_obj = Login.objects.get(Username=username, Password=password)
        if login_obj.Type == "admin":
            auth_obj = auth.authenticate(username='mrudu', password='mrudu')
            if auth_obj is not None:
                auth.login(request, auth_obj)
            return redirect("admin_home")

        elif login_obj.Type == "shop":
            auth_obj = auth.authenticate(username='mrudul', password='mrudul')
            if auth_obj is not None:
                auth.login(request, auth_obj)
            request.session['shop_id'] = login_obj.id
            return redirect("shop_home")
        elif login_obj.Type == "blocked":
            return HttpResponse('''<script>alert ("sorry you are blocked!");window.location="/"</script>''')
    except:
        return HttpResponse('''<script>alert ("incorrect username or password");window.location="/"</script>''')


@login_required(login_url='/')
def admin_home(request):
    return render(request, "admin/admin_home_index.html")


@login_required(login_url='/')
def manage_workers(request):
    worker_obj = Workers.objects.all()
    return render(request, "admin/manage_workers.html", {'worker_obj': worker_obj})


@login_required(login_url='/')
def manage_workers1(request):
    s = request.POST['textfield']
    worker_obj = Workers.objects.filter(FirstName__istartswith=s)
    return render(request, "admin/manage_workers.html", {'worker_obj': worker_obj, "s": s})


@login_required(login_url='/')
def add_workers(request):
    return render(request, "admin/add_workers.html")


@login_required(login_url='/')
def add_workers_action(request):
    first_name = request.POST["FirstName"]
    last_name = request.POST["LastName"]
    age = request.POST["Age"]
    gender = request.POST["Gender"]
    place = request.POST["Place"]
    post = request.POST["Post"]
    pin = request.POST["Pin"]
    phone = request.POST["Phone"]
    field = request.POST["Field"]
    latitude = request.POST["Latitude"]
    longitude = request.POST["Longitude"]
    username = request.POST["Username"]
    password = request.POST["Password"]
    login_obj = Login()
    login_obj.Username = username
    login_obj.Password = password
    login_obj.Type = 'worker'
    login_obj.save()

    worker_obj = Workers()
    worker_obj.FirstName = first_name
    worker_obj.LastName = last_name
    worker_obj.Age = age
    worker_obj.Gender = gender
    worker_obj.Place = place
    worker_obj.Post = post
    worker_obj.Pin = pin
    worker_obj.Phone = phone
    worker_obj.Field = field
    worker_obj.Latitude = latitude
    worker_obj.Longitude = longitude
    worker_obj.LOGIN_ID = login_obj
    worker_obj.save()
    return HttpResponse('''<script>alert("worker added successfully");window.location="/manage_workers#about"</script>''')


@login_required(login_url='/')
def workers_update(request, worker_id):
    request.session['worker_id'] = worker_id
    worker_obj = Workers.objects.get(id=worker_id)
    return render(request, "admin/update_worker.html", {'worker_obj': worker_obj})


@login_required(login_url='/')
def update_workers_action(request):
    worker_obj = Workers.objects.get(id=request.session['worker_id'])
    first_name = request.POST["FirstName"]
    last_name = request.POST["LastName"]
    age = request.POST["Age"]
    gender = request.POST["Gender"]
    place = request.POST["Place"]
    post = request.POST["Post"]
    pin = request.POST["Pin"]
    phone = request.POST["Phone"]
    field = request.POST["Field"]

    worker_obj.FirstName = first_name
    worker_obj.LastName = last_name
    worker_obj.Age = age
    worker_obj.Gender = gender
    worker_obj.Place = place
    worker_obj.Post = post
    worker_obj.Pin = pin
    worker_obj.Phone = phone
    worker_obj.Field = field
    worker_obj.save()
    return HttpResponse('''<script>alert("worker update successfully");window.location="/manage_workers#about"</script>''')


@login_required(login_url='/')
def workers_delete(request, worker_id):
    worker_obj = Workers.objects.get(id=worker_id)
    login_obj = Login.objects.get(id=worker_obj.LOGIN_ID_id)
    login_obj.delete()
    return HttpResponse('''<script>alert("worker delete successfully");window.location="/manage_workers#about"</script>''')


@login_required(login_url='/')
def manage_shop(request):
    shop_obj = Shop.objects.all()
    return render(request, "admin/manage_shop.html", {'shop_obj': shop_obj})


@login_required(login_url='/')
def manage_shop1(request):
    search = request.POST["search_name"]
    shop_obj = Shop.objects.filter(Name__istartswith=search)
    return render(request, "admin/manage_shop.html", {'shop_obj': shop_obj, 'Search': search})


@login_required(login_url='/')
def add_shop(request):
    return render(request, "admin/add_shop.html")


@login_required(login_url='/')
def add_shop_action(request):
    shop_name = request.POST["Name"]
    place = request.POST["Place"]
    post = request.POST["Post"]
    pin = request.POST["Pin"]
    phone = request.POST["Phone"]
    email = request.POST["Email"]
    username = request.POST["Username"]
    password = request.POST["Password"]

    login_obj = Login()
    login_obj.Username = username
    login_obj.Password = password
    login_obj.Type = 'shop'
    login_obj.save()

    shop_obj = Shop()
    shop_obj.Name = shop_name
    shop_obj.Place = place
    shop_obj.Post = post
    shop_obj.Pin = pin
    shop_obj.Phone = phone
    shop_obj.Email = email
    shop_obj.LOGIN_ID = login_obj
    shop_obj.save()

    return HttpResponse('''<script>alert("shop added successfully");window.location="/manage_shop#about"</script>''')


@login_required(login_url='/')
def shop_update(request, shop_id):
    request.session['shop_id'] = shop_id
    shop_obj = Shop.objects.get(id=shop_id)
    return render(request, "admin/update_shop.html", {'shop_obj': shop_obj})


@login_required(login_url='/')
def update_shop_action(request):
    shop_obj = Shop.objects.get(id=request.session['shop_id'])
    shop_name = request.POST["Name"]
    place = request.POST["Place"]
    post  = request.POST["Post"]
    pin = request.POST["Pin"]
    phone = request.POST["Phone"]
    email = request.POST["Email"]

    shop_obj.Name = shop_name
    shop_obj.Place = place
    shop_obj.Post = post
    shop_obj.Pin = pin
    shop_obj.Phone = phone
    shop_obj.Email = email
    shop_obj.save()

    return HttpResponse('''<script>alert("shop updated successfully");window.location="/manage_shop#about"</script>''')


@login_required(login_url='/')
def shop_delete(request, shop_id):
    shop_obj = Shop.objects.get(id=shop_id)
    login_obj = Login.objects.get(id=shop_obj.LOGIN_ID_id)
    login_obj.delete()
    return HttpResponse('''<script>alert("shop deleted successfully");window.location="/manage_shop#about"</script>''')


@login_required(login_url='/')
def block_unblock_shop(request):
    shop_obj = Shop.objects.all()
    return render(request, "admin/block_unblock_shop.html", {'shop_obj': shop_obj})


@login_required(login_url='/')
def shop_block(request, shop_id):
    shop_obj = Shop.objects.get(id=shop_id)
    login_obj = Login.objects.get(id=shop_obj.LOGIN_ID.id)
    login_obj.Type = "shop blocked"
    login_obj.save()
    return HttpResponse('''<script>alert("shop blocked ");window.location="/block_unblock_shop#about"</script>''')


@login_required(login_url='/')
def shop_unblock(request, shop_id):
    shop_obj = Shop.objects.get(id=shop_id)
    login_obj = Login.objects.get(id=shop_obj.LOGIN_ID.id)
    login_obj.Type = "shop"
    login_obj.save()
    return HttpResponse('''<script>alert("shop unblocked ");window.location="/block_unblock_shop#about"</script>''')


@login_required(login_url='/')
def block_unblock_workers(request):
    workers_obj = Workers.objects.all()
    return render(request, "admin/block_unblock_workers.html", {'workers_obj': workers_obj})


@login_required(login_url='/')
def worker_block(request, worker_id):
    worker_obj = Workers.objects.get(id=worker_id)
    login_obj = Login.objects.get(id=worker_obj.LOGIN_ID.id)
    login_obj.Type = "worker blocked"
    login_obj.save()
    return HttpResponse('''<script>alert("worker blocked ");window.location="/block_unblock_workers#about"</script>''')


@login_required(login_url='/')
def worker_unblock(request, worker_id):
    worker_obj = Workers.objects.get(id=worker_id)
    login_obj = Login.objects.get(id=worker_obj.LOGIN_ID.id)
    login_obj.Type = "worker"
    login_obj.save()
    return HttpResponse('''<script>alert("worker unblocked ");window.location="/block_unblock_workers#about"</script>''')


@login_required(login_url='/')
def verify_users(request):
    user_obj = User.objects.all()
    return render(request, "admin/verify_users.html", {'user_obj': user_obj})


@login_required(login_url='/')
def user_accept(request, user_id):
    print(user_id)
    user_obj = User.objects.get(id=user_id)
    login_obj = Login.objects.get(id=user_obj.LOGIN_ID.id)
    login_obj.Type = "user"
    login_obj.save()
    return HttpResponse('''<script>alert("Accepted successfully");window.location="/verify_users#about"</script>''')


@login_required(login_url='/')
def user_reject(request, user_id):
    print(user_id)
    user_obj = User.objects.get(id=user_id)
    login_obj = Login.objects.get(id=user_obj.LOGIN_ID.id)
    login_obj.Type = "rejected"
    login_obj.save()
    return HttpResponse('''<script>alert("Rejected successfully");window.location="/verify_users#about"</script>''')


def search_complaint_type_admin(request):
    return render(request, "admin/search_complaint_type_admin.html")


@login_required(login_url='/')
def view_complaints_reply(request):
    search_type = request.POST['select']
    complaint_obj = Complaint.objects.filter(LOGIN_ID__Type=search_type)
    for i in complaint_obj:
        try:
            obu = User.objects.get(LOGIN_ID__id=i.LOGIN_ID.id)
            i.name = obu.FirstName+" "+obu.LastName
        except:
            obu = Workers.objects.get(LOGIN_ID__id=i.LOGIN_ID.id)
            i.name = obu.FirstName + " " + obu.LastName

    return render(request, "admin/view_complaints.html", {'complaint_obj': complaint_obj})


@login_required(login_url='/')
def complaint_search(request):
    search = request.POST["select"]
    complaint_obj = Complaint.objects.filter(SHOP_ID__Name__istartswith=search)
    for i in complaint_obj:
        try:
            obu = User.objects.get(LOGIN_ID__id=i.LOGIN_ID.id)
            i.name = obu.FirstName+" "+obu.LastName
        except:
            obu = Workers.objects.get(LOGIN_ID__id=i.LOGIN_ID.id)
            i.name = obu.FirstName + " " + obu.LastName
    return render(request, "admin/view_complaints.html", {'complaint_obj': complaint_obj})


@login_required(login_url='/')
def view_products(request):
    product_obj = Product.objects.all()
    return render(request, "admin/view_products.html", {"product_obj": product_obj})


def search_product_admin(request):
    search_product = request.POST['search_product']
    product_obj = Product.objects.filter(Product_Name__istartswith=search_product)
    return render(request, "admin/view_products.html", {"product_obj": product_obj, 'name': search_product})


@login_required(login_url='/')
def view_users(request):
    user_obj = User.objects.all()
    return render(request, "admin/view_users.html", {'user_obj': user_obj})


@login_required(login_url='/')
def view_user_search(request):
    search = request.POST["search_name"]
    user_obj = User.objects.filter(FirstName__istartswith=search)
    return render(request, "admin/view_users.html", {'user_obj': user_obj, 'Search': search})


#      shop

@login_required(login_url='/')
def shop_home(request):
    return render(request, "shop/shop_home_index.html")


@login_required(login_url='/')
def manage_delivery_boy(request):
    boy_obj = DeliveryBoy.objects.all()
    return render(request, "shop/manage_delivery_boy.html", {'boy_obj': boy_obj})


@login_required(login_url='/')
def search_delivery_boy(request):
    boy_name = request.POST["select_boy"]
    boy_obj = DeliveryBoy.objects.filter(FirstName__istartswith=boy_name)
    return render(request, "shop/manage_delivery_boy.html", {'boy_obj': boy_obj, 'boy_name': boy_name})


@login_required(login_url='/')
def add_delivery_boy(request):
    return render(request, "shop/add_delivery_boy.html")


@login_required(login_url='/')
def add_delivery_boy_action(request):
    first_name = request.POST["FirstName"]
    last_name = request.POST["LastName"]
    age = request.POST["Age"]
    gender = request.POST["Gender"]
    place = request.POST["Place"]
    post = request.POST["Post"]
    pin = request.POST["Pin"]
    phone = request.POST["Phone"]
    email = request.POST["Email"]
    username = request.POST["Username"]
    password = request.POST["Password"]

    login_obj = Login()
    login_obj.Username = username
    login_obj.Password = password
    login_obj.Type = 'DeliveryBoy'
    login_obj.save()

    delivery_boy_obj = DeliveryBoy()
    delivery_boy_obj.FirstName = first_name
    delivery_boy_obj.LastName = last_name
    delivery_boy_obj.Age = age
    delivery_boy_obj.Gender = gender
    delivery_boy_obj.Place = place
    delivery_boy_obj.Post = post
    delivery_boy_obj.Pin = pin
    delivery_boy_obj.Phone = phone
    delivery_boy_obj.Email = email
    delivery_boy_obj.LOGIN_ID = login_obj
    delivery_boy_obj.SHOP_ID = Shop.objects.get(LOGIN_ID_id=request.session['shop_id'])
    delivery_boy_obj.save()
    return HttpResponse('''<script>alert("delivery boy added successfully");
    window.location="/manage_delivery_boy"</script>''')


@login_required(login_url='/')
def edit_delivery_boy(request, boy_id):
    request.session['boy_id'] = boy_id
    boy_obj = DeliveryBoy.objects.get(id=boy_id)
    return render(request, "shop/edit_delivery_boy.html", {'boy_obj': boy_obj})


@login_required(login_url='/')
def edit_delivery_boy_action(request):
    first_name = request.POST["FirstName"]
    last_name = request.POST["LastName"]
    age = request.POST["Age"]
    gender = request.POST["Gender"]
    place = request.POST["Place"]
    post = request.POST["Post"]
    pin = request.POST["Pin"]
    phone = request.POST["Phone"]
    email = request.POST["Email"]
    username = request.POST["Username"]
    password = request.POST["Password"]
    delivery_boy_obj = DeliveryBoy.objects.get(id=request.session['boy_id'])

    login_obj = Login.objects.get(id=delivery_boy_obj.LOGIN_ID.id)
    login_obj.Username = username
    login_obj.Password = password
    login_obj.Type = 'DeliveryBoy'
    login_obj.save()

    delivery_boy_obj.FirstName = first_name
    delivery_boy_obj.LastName = last_name
    delivery_boy_obj.Age = age
    delivery_boy_obj.Gender = gender
    delivery_boy_obj.Place = place
    delivery_boy_obj.Post = post
    delivery_boy_obj.Pin = pin
    delivery_boy_obj.Phone = phone
    delivery_boy_obj.Email = email
    delivery_boy_obj.LOGIN_ID = login_obj
    delivery_boy_obj.save()
    return HttpResponse('''<script>alert("delivery boy edited successfully");
        window.location="/manage_delivery_boy"</script>''')


@login_required(login_url='/')
def delete_delivery_boy(request, boy_id):
    boy_obj = DeliveryBoy.objects.get(id=boy_id)
    boy_obj.delete()
    return HttpResponse('''<script>alert("delivery boy removed successfully");
            window.location="/manage_delivery_boy"</script>''')


@login_required(login_url='/')
def assign_delivery_boy(request):
    request_obj = ProductRequest.objects.filter(PRODUCT_ID__SHOP_ID__LOGIN_ID_id=request.session['shop_id'], Status="Accepted")
    return render(request, "shop/assign_delivery_boy.html", {'request_obj': request_obj})


@login_required(login_url='/')
def assign(request, assign_id):
    request.session['assign_id'] = assign_id
    shop_obj = Shop.objects.get(LOGIN_ID_id=request.session['shop_id'])
    boy_obj = DeliveryBoy.objects.filter(SHOP_ID_id=shop_obj.id)
    return render(request, "shop/assign.html", {'boy_obj': boy_obj})


@login_required(login_url='/')
def assign_action(request, boy_id):
    print("bou_id", boy_id)
    request_obj = ProductRequest.objects.get(id=request.session['assign_id'])
    boy_obj = DeliveryBoy.objects.get(id=boy_id)
    assign_obj = Assign()
    assign_obj.Date = datetime.now().strftime('%d/%m/%y')
    assign_obj.Status = "Assigned"
    assign_obj.PRODUCT_ID = request_obj.PRODUCT_ID
    assign_obj.DELIVERY_BOY_ID = boy_obj
    assign_obj.PRODUCT_REQUEST_ID = request_obj
    assign_obj.save()
    return HttpResponse('''<script>alert("Assigned successfully");window.location="/assign_delivery_boy"</script>''')


@login_required(login_url='/')
def manage_product(request):
    product_obj = Product.objects.all()
    return render(request, "shop/manage_product.html", {'product_obj': product_obj})


@login_required(login_url='/')
def manage_product_search(request):
    product_type = request.POST["select_product"]
    product_obj = Product.objects.filter(Product_Type__istartswith=product_type)
    return render(request, "shop/manage_product.html", {'product_obj': product_obj})


@login_required(login_url='/')
def add_product(request):
    return render(request, "shop/Add_products.html")


@login_required(login_url='/')
def add_product_action(request):
    product_name = request.POST["ProductName"]
    product_type = request.POST["ProductType"]
    product_details = request.POST["ProductDetails"]
    product_price = request.POST["ProductPrice"]
    product_image = request.FILES["ProductImage"]
    fss = FileSystemStorage()
    image = fss.save(product_image.name, product_image)
    product_stock = request.POST["ProductStock"]

    product_obj = Product()
    product_obj.Product_Name = product_name
    product_obj.Product_Type = product_type
    product_obj.Product_Details = product_details
    product_obj.Price_per_day = product_price
    product_obj.Image = image
    product_obj.Stock = product_stock
    print("request.session['shop_id']",request.session['shop_id'])
    shop_obj = Shop.objects.get(LOGIN_ID_id=request.session['shop_id'])
    product_obj.SHOP_ID = shop_obj
    product_obj.save()
    return HttpResponse('''<script>alert("Product added successfully");window.location="/manage_product#about"</script>''')


@login_required(login_url='/')
def edit_product(request, product_id):
    request.session['product_id'] = product_id
    product_obj = Product.objects.get(id=product_id)
    return render(request, "shop/edit_product.html", {'product_obj': product_obj})


@login_required(login_url='/')
def edit_product_action(request):
    product_name = request.POST["ProductName"]
    product_type = request.POST["ProductType"]
    product_details = request.POST["ProductDetails"]
    product_price = request.POST["ProductPrice"]
    product_image = request.FILES["ProductImage"]
    fss = FileSystemStorage()
    image = fss.save(product_image.name, product_image)
    product_stock = request.POST["ProductStock"]

    product_obj = Product.objects.get(id=request.session['product_id'])
    product_obj.Product_Name = product_name
    product_obj.Product_Type = product_type
    product_obj.Product_Details = product_details
    product_obj.Price_per_day = product_price
    product_obj.Image = image
    product_obj.Stock = product_stock
    shop_obj = Shop.objects.get(LOGIN_ID_id=request.session['shop_id'])
    product_obj.SHOP_ID = shop_obj
    product_obj.save()
    return HttpResponse('''<script>alert("Editing successfully");window.location="/manage_product#about"</script>''')


@login_required(login_url='/')
def delete_product(request, product_id):
    product_obj = Product.objects.get(id=product_id)
    product_obj.delete()
    return HttpResponse('''<script>alert("Deleted successfully");window.location="/manage_product#about"</script>''')


def search_complaint_type_shop(request):
    return render(request, "shop/search_complaint_type_shop.html")


@login_required(login_url='/')
def view_complaints_shop(request):
    search_type = request.POST['select']
    complaint_obj = Complaint.objects.filter(SHOP_ID__LOGIN_ID=request.session['shop_id'], LOGIN_ID__Type=search_type)
    for i in complaint_obj:
        try:
            print(i.id)
            print(i.LOGIN_ID.id)
            obu = User.objects.get(LOGIN_ID__id=i.LOGIN_ID.id, LOGIN_ID__Type=i.LOGIN_ID.Type)
            i.name = obu.FirstName + " " + obu.LastName
        except:
            print(i.id)
            print(i.LOGIN_ID.id)
            obu = Workers.objects.get(LOGIN_ID__id=i.LOGIN_ID.id, LOGIN_ID__Type=i.LOGIN_ID.Type)
            i.name = obu.FirstName + " " + obu.LastName

    return render(request, "shop/view_complaints_reply.html", {'complaint_obj': complaint_obj})


@login_required(login_url='/')
def complaint_search_date(request):
    date = request.POST["Date"]
    print(date, "NNNNNNNNNNNNNNNNN")
    complaint_obj = Complaint.objects.filter(Date=date)
    return render(request, "shop/view_complaints_reply.html", {'complaint_obj': complaint_obj, 'date': date})


@login_required(login_url='/')
def send_reply_shop(request, reply_id):
    request.session['reply_id'] = reply_id
    return render(request, "shop/reply.html")


@login_required(login_url='/')
def send_reply_shop_action(request):
    complaint_obj = Complaint.objects.get(id=request.session['reply_id'])
    reply = request.POST['Reply']
    complaint_obj.Reply = reply
    complaint_obj.save()
    return HttpResponse('''<script>alert("replied");window.location="/shop_home#about"</script>''')


@login_required(login_url='/')
def view_request_update_status(request):
    request_obj = ProductRequest.objects.filter(LOGIN_ID__Type="user")
    for i in request_obj:
        user_obj = User.objects.get(LOGIN_ID__id=i.LOGIN_ID.id)
        i.name = user_obj.FirstName + "" + user_obj.LastName
        i.address = user_obj.Place + " " + user_obj.Post + " " + str(user_obj.Pin)
        i.phone = user_obj.Phone
    return render(request, "shop/view_request_update_status.html", {'request_obj': request_obj})


@login_required(login_url='/')
def date_assign(request, request_id):
    request.session['request_id'] = request_id
    return render(request, "shop/date_assign.html")


@login_required(login_url='/')
def product_accept(request,):
    date = request.POST["date"]
    request_obj = ProductRequest.objects.get(id=request.session['request_id'])
    request_obj.ReturnDate = date
    request_obj.Status = "Accepted"
    request_obj.save()
    return HttpResponse('''<script>alert("Accepted");window.location="/view_request_update_status"</script>''')


@login_required(login_url='/')
def product_reject(request, request_id):
    request_obj = ProductRequest.objects.get(id=request_id)
    request_obj.Status = "rejected"
    request_obj.ReturnDate = "rejected"
    request_obj.save()
    return HttpResponse('''<script>alert("Rejected");window.location="/view_request_update_status#about"</script>''')


# /////////////////////////////////// webservice ////////////////////////////////////////


def login_code(request):
    username = request.POST['username']
    password = request.POST['password']
    try:
        login_obj = Login.objects.get(Username=username, Password=password)
        if login_obj is None:
            data = {"task": "invalid"}
            return JsonResponse(data)
        elif login_obj.Type == "worker":
            request.session['worker_id'] = login_obj.id
            data = {"task": "worker", "id": login_obj.id}
            return JsonResponse(data)
        elif login_obj.Type == "user":
            request.session['user_id'] = login_obj.id
            data = {"task": "user", "id": login_obj.id}
            return JsonResponse(data)

        elif login_obj.Type == "DeliveryBoy":
            data = {"task": "boy", "id": login_obj.id, 'type': login_obj.Type}
            return JsonResponse(data)

    except Exception as e:
        print(e)
        data = {"task": "invalid"}
        return JsonResponse(data)


def manage_skill(request):
    worker_id = request.POST['lid']
    print("##################################", worker_id)
    skill_obj = Skills.objects.filter(WORKER_ID__LOGIN_ID_id=worker_id)
    skill_data = []
    for i in skill_obj:
        data = {'Skill': i.Skill, 'Type': i.WorkType, 'Date': i.Date,
                'Exp': i.Exp, 'skill_id': i.id}
        skill_data.append(data)
    r = json.dumps(skill_data)
    return HttpResponse(r)


def add_new_skill(request):
    worker_id = request.POST["lid"]
    print('%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%', worker_id)
    worker_obj = Workers.objects.get(LOGIN_ID_id=worker_id)
    skill = request.POST["Skill"]
    work_type = request.POST["Type"]
    experience = request.POST["Exp"]
    skill_obj = Skills()
    skill_obj.Skill = skill
    skill_obj.WorkType = work_type
    skill_obj.Exp = experience
    skill_obj.Date = datetime.now().strftime("%d/%m/%y")
    skill_obj.WORKER_ID = worker_obj
    skill_obj.save()
    data = {'task': 'success'}
    r = json.dumps(data)
    return HttpResponse(r)


def delete_skill(request):
    skill_id = request.POST["skill_id"]
    skill_obj = Skills.objects.get(id=skill_id)
    skill_obj.delete()
    data = {'task': 'success'}
    r = json.dumps(data)
    return HttpResponse(r)


def view_work_request_response(request):
    login_id = request.POST["lid"]
    worker_obj = Workers.objects.get(LOGIN_ID_id=login_id)
    request_obj = WorkersRequest.objects.filter(WORKER_ID_id=worker_obj.id)
    request_data = []
    for i in request_obj:
        data = {'Firstname': i.USER_ID.FirstName, 'Lastname': i.USER_ID.LastName, 'Date': i.Date, 'Status': i.Status,
                'Work_details': i.Work_Details, 'WorkRequest_id': i.id}
        request_data.append(data)
    r = json.dumps(request_data)
    return HttpResponse(r)


def accept_work_request(request):
    work_request_id = request.POST["work_request_id"]
    request_obj = WorkersRequest.objects.get(id=work_request_id)
    request_obj.Status = "Accepted"
    request_obj.save()
    data = {'task': 'success'}
    r = json.dumps(data)
    return HttpResponse(r)


def reject_work_request(request):
    work_request_id = request.POST["work_request_id"]
    request_obj = WorkersRequest.objects.get(id=work_request_id)
    request_obj.Status = "Rejected"
    request_obj.save()
    data = {'task': 'success'}
    r = json.dumps(data)
    return HttpResponse(r)


def view_products_send_request(request):
    product_obj = Product.objects.all()
    product_data = []
    for i in product_obj:
        data = {'Product': i.Product_Name, 'Type': i.Product_Type, 'Details': i.Product_Details,
                'Image': str(i.Image.url), 'Price': i.Price_per_day, 'product_id': i.id}
        product_data.append(data)
    r = json.dumps(product_data)
    return HttpResponse(r)


def product_request_send(request):
    login_id = request.POST["u_lid"]
    product_id = request.POST["product_id"]
    login_obj = Login.objects.get(id=login_id)
    user_obj = User.objects.get(LOGIN_ID_id=login_id)
    product_obj = Product.objects.get(id=product_id)
    request_obj = ProductRequest()
    request_obj.Date = datetime.now().strftime("%d/%m/%y")
    request_obj.Status = "pending"
    request_obj.ReturnDate = "pending"
    request_obj.PRODUCT_ID = product_obj
    request_obj.LOGIN_ID = login_obj
    request_obj.Latitude = user_obj.Latitude
    request_obj.Longitude = user_obj.Longitude
    request_obj.address = user_obj.FirstName + "," + user_obj.LastName + "," + user_obj.Place + "," + user_obj.Post + "," + str(user_obj.Pin)
    request_obj.save()
    data = {'task': 'success'}
    r = json.dumps(data)
    return HttpResponse(r)


def product_request_send_worker(request):
    login_id = request.POST["w_lid"]
    print('################################', login_id)
    worker_obj = Workers.objects.get(LOGIN_ID_id=login_id)
    product_id = request.POST["product_id"]
    login_obj = Login.objects.get(id=login_id)
    product_obj = Product.objects.get(id=product_id)
    request_obj = ProductRequest()
    request_obj.Date = datetime.now().strftime("%d/%m/%y")
    request_obj.Status = "pending"
    request_obj.ReturnDate = "pending"
    request_obj.PRODUCT_ID = product_obj
    request_obj.LOGIN_ID = login_obj
    request_obj.Longitude = worker_obj.Longitude
    request_obj.Latitude = worker_obj.Latitude
    request_obj.address = worker_obj.FirstName + "," + worker_obj.LastName + "," + worker_obj.Place + "," + worker_obj.Post + "," + str(worker_obj.Pin)
    request_obj.save()
    data = {'task': 'success'}
    r = json.dumps(data)
    return HttpResponse(r)


def view_product_name(request):
    product_obj = Product.objects.all()
    product_data = []
    for i in product_obj:
        data = {'Product_name': i.Product_Name, 'Product_id': i.id}
        product_data.append(data)
    r = json.dumps(product_data)
    return HttpResponse(r)


def worker_complaints_reply(request):
    user_id = request.POST['lid']
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@", user_id)
    complaint_obj = Complaint.objects.filter(LOGIN_ID_id=user_id)
    data = []
    for i in complaint_obj:
        row = {'Complaint': i.Complaints, 'Reply': i.Reply, 'Date': str(i.Date)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def registration(request):
    firstname = request.POST['Firstname']
    lastname = request.POST['Lastname']
    age = request.POST['Age']
    gender = request.POST['Gender']
    place = request.POST['Place']
    post_office = request.POST['Post']
    pin_code = request.POST['Pin']
    phone = request.POST['Phone']
    email_id = request.POST['Email']
    latitude = request.POST['Latitude']
    longitude = request.POST['Longitude']
    # proof = request.FILES['proof']
    # fss = FileSystemStorage()
    # proof_file = fss.save(proof.name, proof)
    username = request.POST['Username']
    password = request.POST['Password']

    lob = Login()
    lob.Username = username
    lob.Password = password
    lob.Type = 'user'
    lob.save()

    user_obj = User()
    user_obj.FirstName = firstname
    user_obj.LastName = lastname
    user_obj.Age = age
    user_obj.Gender = gender
    user_obj.Place = place
    user_obj.Post = post_office
    user_obj.Pin = pin_code
    user_obj.Phone = phone
    user_obj.Email = email_id
    user_obj.Latitude = latitude
    user_obj.Longitude = longitude
    # user_obj.Proof = proof_file
    user_obj.LOGIN_ID = lob
    user_obj.save()
    data = {"task": "success"}
    r = json.dumps(data)
    return HttpResponse(r)


def view_message2(request):
    print(request.POST)
    fromid = request.POST['fid']
    toid = request.POST['toid']
    mid = request.POST['lastmsgid']

    ob1 = Chat.objects.filter(FROM_ID__id=fromid, TO_ID__id=toid, id__gt=mid)
    ob2 = Chat.objects.filter(FROM_ID__id=toid, TO_ID__id=fromid, id__gt=mid)
    ob = ob1.union(ob2)
    ob = ob.order_by("id")
    data = []
    for i in ob:
        r = {"msgid": i.id, "date": str(i.Date), "message": i.Message, "fromid": i.FROM_ID.id}
        data.append(r)
    if len(data) > 0:
        return JsonResponse({"status": "ok", "res1": data})
    else:
        return JsonResponse({"status": "na"})


def in_message2(request):
    print(request.POST)
    fromid = request.POST['fid']
    toid = request.POST['toid']
    message = request.POST['msg']
    chat_obj = Chat()
    chat_obj.FROM_ID = Login.objects.get(id=fromid)
    chat_obj.TO_ID = Login.objects.get(id=toid)
    chat_obj.Message = message
    chat_obj.Date = datetime.now().strftime("%d-%m-%y")
    chat_obj.Time = datetime.now().strftime("%H:%M:%S")
    chat_obj.save()
    data = {"status": "send"}
    r = json.dumps(data)
    return HttpResponse(r)


def view_workers(request):
    worker_obj = Workers.objects.all()
    worker_data = []
    for i in worker_obj:
        data = {'first_name': i.FirstName, 'last_name': i.LastName, 'worker_id': i.id}
        worker_data.append(data)
    r = json.dumps(worker_data)
    # return JsonResponse(worker_data)
    return HttpResponse(r)


def view_worker_response(request):
    login_id = request.POST["lid"]
    user_obj = User.objects.get(LOGIN_ID_id=login_id)
    request_obj = WorkersRequest.objects.filter(USER_ID_id=user_obj.id)
    request_data = []
    for i in request_obj:
        data = {'Firstname': i.WORKER_ID.FirstName, 'Lastname': i.WORKER_ID.LastName, 'Phone': i.WORKER_ID.Phone,
                'Date': i.Date, 'Status': i.Status, 'Work_details': i.Work_Details, 'WorkRequest_id': i.id}
        request_data.append(data)
    r = json.dumps(request_data)
    return HttpResponse(r)


def send_request_for_worker_view(request):
    skill_obj = Skills.objects.all()
    worker_data = []
    for i in skill_obj:
        data = {'worker_firstname': i.WORKER_ID.FirstName, 'worker_lastname': i.WORKER_ID.LastName,
                'worker_phone': i.WORKER_ID.Phone, 'worker_experience': i.Exp, 'worker_place': i.WORKER_ID.Place,
                'worker_post': i.WORKER_ID.Post, 'worker_pin': i.WORKER_ID.Pin, 'worker_field': i.WORKER_ID.Field,
                'requested_worker_id': i.WORKER_ID.id}
        worker_data.append(data)
    r = json.dumps(worker_data)
    return HttpResponse(r)


def add_worker_request(request):
    work_details = request.POST["work_details"]
    work_date = request.POST["work_date"]
    user_id = request.POST["user_id"]
    print("99999999999999999999999999999", user_id)
    Worker_id = request.POST["worker_id"]
    request_obj = WorkersRequest()
    request_obj.Date = work_date
    request_obj.Status = "pending"
    request_obj.Work_Details = work_details
    request_obj.USER_ID = User.objects.get(LOGIN_ID_id=user_id)
    request_obj.WORKER_ID = Workers.objects.get(id=Worker_id)
    request_obj.save()
    data = {"task": "success"}
    r = json.dumps(data)
    return HttpResponse(r)


def view_product_response(request):
    login_id = request.POST["lid"]
    # user_obj = User.objects.get(LOGIN_ID_id=login_id)
    request_obj = ProductRequest.objects.filter(LOGIN_ID_id=login_id)
    request_data = []
    for i in request_obj:
        data = {'Firstname': i.PRODUCT_ID.Product_Name, 'Phone': i.Date, 'Date': i.Status, 'Status': i.ReturnDate,
                'productRequest_id': i.id, 'latitude': i.Latitude, 'longitude': i.Latitude}
        request_data.append(data)
    r = json.dumps(request_data)
    return HttpResponse(r)


def view_users_for_chat(request):
    user_obj = User.objects.all()
    user_data = []
    for i in user_obj:
        data = {'first_name': i.FirstName, 'last_name': i.LastName, 'user_id': i.id}
        user_data.append(data)
    r = json.dumps(user_data)
    # return JsonResponse(worker_data)
    return HttpResponse(r)


def send_rating(request):
    rating = request.POST["Rating"]
    review = request.POST["Review"]
    lid = request.POST["lid"]
    product_id = request.POST["Product_id"]
    rating_obj = Ratings()
    rating_obj.Rating = rating
    rating_obj.Date = datetime.now().strftime("%d-%m-%y")
    rating_obj.Review = review
    rating_obj.LOGIN_ID = Login.objects.get(id=lid)
    rating_obj.PRODUCT_ID = Product.objects.get(id=product_id)
    rating_obj.save()
    data = {"task": "success"}
    r = json.dumps(data)
    return HttpResponse(r)


def view_shop_name(request):
    shop_obj = Shop.objects.all()
    shop_data = []
    for i in shop_obj:
        data = {'shop_name': i.Name, 'shop_id': i.id}
        shop_data.append(data)
    r = json.dumps(shop_data)
    return HttpResponse(r)


def send_shop_complaint(request):
    complaint = request.POST["complaint"]
    login_id = request.POST["lid"]
    shop_id = request.POST["shop_id"]
    complaint_obj = Complaint()
    complaint_obj.Complaints = complaint
    complaint_obj.Date = datetime.now().strftime("%d-%m-%y")
    complaint_obj.Reply = "pending"
    complaint_obj.LOGIN_ID = Login.objects.get(id=login_id)
    complaint_obj.SHOP_ID = Shop.objects.get(id=shop_id)
    complaint_obj.save()
    data = {"task": "success"}
    r = json.dumps(data)
    return HttpResponse(r)


def user_complaints_reply(request):
    user_id = request.POST['user_lid']
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@", user_id)
    complaint_obj = Complaint.objects.filter(LOGIN_ID_id=user_id)
    data = []
    for i in complaint_obj:
        row = {'Complaint': i.Complaints, 'Reply': i.Reply, 'Date': str(i.Date)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def view_assigned_work(request):
    boy_id = request.POST["lid"]
    print("@@@@@@@@@@@@@@@@@@", boy_id)
    request_obj = Assign.objects.filter(DELIVERY_BOY_ID__LOGIN_ID__id=boy_id)
    print("(((((((((((((((((((((((((((((", request_obj)
    request_data = []
    for i in request_obj:
        try:
            print("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%", i.PRODUCT_REQUEST_ID.LOGIN_ID)
            user_obj = User.objects.get(LOGIN_ID__id=i.PRODUCT_REQUEST_ID.LOGIN_ID.id)
            print(user_obj,"JJJJJJJJJJJJ")
            data = {'Product': i.PRODUCT_REQUEST_ID.PRODUCT_ID.Product_Name, 'Details': i.PRODUCT_REQUEST_ID.PRODUCT_ID.Product_Details,
                    'Assign_id': i.id, "Phone": user_obj.Phone, "Name": user_obj.FirstName+" "+user_obj.LastName,"lat": i.PRODUCT_REQUEST_ID.Latitude,
                    "lon": i.PRODUCT_REQUEST_ID.Longitude, "address": i.PRODUCT_REQUEST_ID.address}
            request_data.append(data)
        except:
            print("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%", i.PRODUCT_REQUEST_ID.LOGIN_ID)
            user_obj = Workers.objects.get(LOGIN_ID__id=i.PRODUCT_REQUEST_ID.LOGIN_ID.id)
            print(user_obj, "JJJJJJJJJJJJ")
            data = {'Product': i.PRODUCT_REQUEST_ID.PRODUCT_ID.Product_Name,
                    'Details': i.PRODUCT_REQUEST_ID.PRODUCT_ID.Product_Details,
                    'Assign_id': i.id, "Phone": user_obj.Phone, "Name": user_obj.FirstName + " " + user_obj.LastName,
                    "lat": i.PRODUCT_REQUEST_ID.Latitude,
                    "lon": i.PRODUCT_REQUEST_ID.Longitude, "address": i.PRODUCT_REQUEST_ID.address}
            request_data.append(data)

    r = json.dumps(request_data)
    return HttpResponse(r)


def search_worker(request):
    search = request.POST["search_worker"]

    skill_obj = Skills.objects.filter(WORKER_ID__Field=search)
    worker_data = []
    for i in skill_obj:
        data = {'worker_firstname': i.WORKER_ID.FirstName, 'worker_lastname': i.WORKER_ID.LastName,
                'worker_phone': i.WORKER_ID.Phone, 'worker_experience': i.Exp, 'worker_place': i.WORKER_ID.Place,
                'worker_post': i.WORKER_ID.Post, 'worker_pin': i.WORKER_ID.Pin, 'worker_field': i.WORKER_ID.Field,
                'requested_worker_id': i.WORKER_ID.id}
        worker_data.append(data)
    r = json.dumps(worker_data)
    print(worker_data)
    return HttpResponse(r)


def search_product_user(request):
    search = request.POST["search_product"]
    product_obj = Product.objects.filter(Product_Type__contains=search)
    product_data = []
    for i in product_obj:
        data = {'Product': i.Product_Name, 'Type': i.Product_Type, 'Details': i.Product_Details,
                'Image': str(i.Image.url), 'Price': i.Price_per_day, 'product_id': i.id}
        product_data.append(data)
    r = json.dumps(product_data)
    return HttpResponse(r)


def search_complaint_user(request):
    user_id = request.POST['user_lid']
    date = request.POST['search_complaint']
    complaint_obj = Complaint.objects.filter(LOGIN_ID_id=user_id, Date=date)
    data = []
    for i in complaint_obj:
        row = {'Complaint': i.Complaints, 'Reply': i.Reply, 'Date': str(i.Date)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def search_skill(request):
    worker_id = request.POST['lid']
    work_type = request.POST['product_type']
    skill_obj = Skills.objects.filter(WORKER_ID__LOGIN_ID_id=worker_id, WorkType=work_type)
    skill_data = []
    for i in skill_obj:
        data = {'Skill': i.Skill, 'Type': i.WorkType, 'Date': i.Date,
                'Exp': i.Exp, 'skill_id': i.id}
        skill_data.append(data)
    r = json.dumps(skill_data)
    return HttpResponse(r)


def search_products_send_request(request):
    searched_product = request.POST['search_product']
    product_obj = Product.objects.filter(Product_Name__icontains=searched_product)
    product_data = []
    for i in product_obj:
        data = {'Product': i.Product_Name, 'Type': i.Product_Type, 'Details': i.Product_Details,
                'Image': str(i.Image.url), 'Price': i.Price_per_day, 'product_id': i.id}
        product_data.append(data)
    r = json.dumps(product_data)
    return HttpResponse(r)


def search_work_request_response(request):
    login_id = request.POST["lid"]
    search_date = request.POST["search_date"]
    worker_obj = Workers.objects.get(LOGIN_ID_id=login_id)
    request_obj = WorkersRequest.objects.filter(WORKER_ID_id=worker_obj.id, Date=search_date)
    request_data = []
    for i in request_obj:
        data = {'Firstname': i.USER_ID.FirstName, 'Lastname': i.USER_ID.LastName, 'Date': i.Date, 'Status': i.Status,
                'Work_details': i.Work_Details, 'WorkRequest_id': i.id}
        request_data.append(data)
    r = json.dumps(request_data)
    return HttpResponse(r)


def search_worker_complaints_reply(request):
    user_id = request.POST['lid']
    search_shop_name = request.POST['shop_name']
    print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@", user_id)
    complaint_obj = Complaint.objects.filter(LOGIN_ID_id=user_id, SHOP_ID__Name__istartswith=search_shop_name);
    data = []
    for i in complaint_obj:
        row = {'Complaint': i.Complaints, 'Reply': i.Reply, 'Date': str(i.Date)}
        data.append(row)
    r = json.dumps(data)
    print("(((((((((((((((((((",data)
    return HttpResponse(r)


def search_assigned_work(request):
    boy_id = request.POST["lid"]
    search_work = request.POST['search_work']
    print("@@@@@@@@@@@@@@@@@@", boy_id)
    request_obj = Assign.objects.filter(DELIVERY_BOY_ID__LOGIN_ID_id=boy_id, Date=search_work)
    request_data = []
    for i in request_obj:
        user_obj = User.objects.get(LOGIN_ID=i.PRODUCT_REQUEST_ID.LOGIN_ID)
        data = {'Product': i.PRODUCT_REQUEST_ID.PRODUCT_ID.Product_Name, 'Details': i.PRODUCT_REQUEST_ID.PRODUCT_ID.Product_Details,
                'Assign_id': i.id, "Phone": user_obj.Phone, "Name": user_obj.FirstName+" "+user_obj.LastName,"lat": i.PRODUCT_REQUEST_ID.Latitude,
                "lon": i.PRODUCT_REQUEST_ID.Longitude, "address": i.PRODUCT_REQUEST_ID.address}
        request_data.append(data)
    r = json.dumps(request_data)
    return HttpResponse(r)


def update_location(request):
    latitude = request.POST['lat']
    longitude = request.POST['lon']
    login_id = request.POST['lid']
    try:

        location_obj = Location.objects.get(DELIVERY_ID__LOGIN_ID__id=login_id)
        location_obj.Latitude = latitude
        location_obj.Longitude = longitude
        location_obj.save()
        data = {"task": "success"}
        r = json.dumps(data)
        return HttpResponse(r)
    except:
        location_obj1 = Location()
        location_obj1.Latitude = latitude
        location_obj1.Longitude = longitude
        del_obj = DeliveryBoy.objects.get(LOGIN_ID__id=login_id)
        location_obj1.DELIVERY_ID = del_obj

        location_obj1.save()
        data = {"task": "success"}
        r = json.dumps(data)
        return HttpResponse(r)
