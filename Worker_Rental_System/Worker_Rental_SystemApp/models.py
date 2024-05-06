from django.db import models


# Create your models here.


class Login(models.Model):
    Username = models.CharField(max_length=20)
    Password = models.CharField(max_length=20)
    Type = models.CharField(max_length=20)


class Shop(models.Model):
    Name = models.CharField(max_length=20)
    Place = models.CharField(max_length=20)
    Post = models.CharField(max_length=20)
    Pin = models.CharField(max_length=20)
    Phone = models.BigIntegerField()
    Email = models.CharField(max_length=20)
    LOGIN_ID = models.ForeignKey(Login, on_delete=models.CASCADE)


class Product(models.Model):
    Product_Type = models.CharField(max_length=30)
    Product_Name = models.CharField(max_length=30)
    Product_Details = models.CharField(max_length=30)
    Price_per_day = models.CharField(max_length=30)
    Image = models.ImageField()
    Stock = models.IntegerField()
    SHOP_ID = models.ForeignKey('Shop', on_delete=models.CASCADE)


class Workers(models.Model):
    FirstName = models.CharField(max_length=20)
    LastName = models.CharField(max_length=20)
    Age = models.IntegerField()
    Gender = models.CharField(max_length=20)
    Place = models.CharField(max_length=20)
    Post = models.CharField(max_length=20)
    Pin = models.IntegerField()
    Phone = models.BigIntegerField()
    Field = models.CharField(max_length=20)
    Latitude = models.FloatField()
    Longitude = models.FloatField()
    LOGIN_ID = models.ForeignKey('Login', on_delete=models.CASCADE)


class User(models.Model):
    FirstName = models.CharField(max_length=20)
    LastName = models.CharField(max_length=20)
    Age = models.IntegerField()
    Gender = models.CharField(max_length=20)
    Place = models.CharField(max_length=20)
    Post = models.CharField(max_length=20)
    Pin = models.IntegerField()
    Phone = models.BigIntegerField()
    Email = models.CharField(max_length=20)
    Latitude = models.FloatField()
    Longitude = models.FloatField()
    LOGIN_ID = models.ForeignKey('Login', on_delete=models.CASCADE)


class Complaint(models.Model):
    Complaints = models.CharField(max_length=100)
    Date = models.CharField(max_length=20)
    Reply = models.CharField(max_length=100)
    SHOP_ID = models.ForeignKey('Shop', on_delete=models.CASCADE)
    LOGIN_ID = models.ForeignKey('Login', on_delete=models.CASCADE)


class WorkersRequest(models.Model):
    Date = models.CharField(max_length=20)
    Status = models.CharField(max_length=20)
    Work_Details = models.CharField(max_length=20)
    WORKER_ID = models.ForeignKey('Workers', on_delete=models.CASCADE)
    USER_ID = models.ForeignKey('User', on_delete=models.CASCADE)


class Chat(models.Model):
    Message = models.CharField(max_length=100)
    Date = models.CharField(max_length=100)
    Time = models.CharField(max_length=100)
    FROM_ID = models.ForeignKey(Login, on_delete=models.CASCADE, related_name="fid")
    TO_ID = models.ForeignKey(Login, on_delete=models.CASCADE, related_name="tid")


class ProductRequest(models.Model):
    Date = models.CharField(max_length=20)
    Status = models.CharField(max_length=20)
    ReturnDate = models.CharField(max_length=20)
    PRODUCT_ID = models.ForeignKey('Product', on_delete=models.CASCADE)
    LOGIN_ID = models.ForeignKey('Login', on_delete=models.CASCADE)
    Latitude = models.FloatField()
    Longitude = models.FloatField()
    address = models.CharField(max_length=500)


class DeliveryBoy(models.Model):
    FirstName = models.CharField(max_length=100)
    LastName = models.CharField(max_length=100)
    Age = models.IntegerField()
    Gender = models.CharField(max_length=100)
    Place = models.CharField(max_length=100)
    Post = models.CharField(max_length=100)
    Pin = models.IntegerField()
    Phone = models.BigIntegerField()
    Email = models.CharField(max_length=100)
    LOGIN_ID = models.ForeignKey('Login', on_delete=models.CASCADE)
    SHOP_ID = models.ForeignKey('Shop', on_delete=models.CASCADE)


class Assign(models.Model):
    Date = models.CharField(max_length=20)
    Status = models.CharField(max_length=20)
    DELIVERY_BOY_ID = models.ForeignKey('DeliveryBoy', on_delete=models.CASCADE)
    PRODUCT_REQUEST_ID = models.ForeignKey('ProductRequest', on_delete=models.CASCADE)


class Ratings(models.Model):
    Rating = models.CharField(max_length=20)
    Date = models.CharField(max_length=20)
    Review = models.CharField(max_length=100)
    LOGIN_ID = models.ForeignKey('Login', on_delete=models.CASCADE)
    PRODUCT_ID = models.ForeignKey('Product', on_delete=models.CASCADE)


class Skills(models.Model):
    Skill = models.CharField(max_length=50)
    Date = models.CharField(max_length=20)
    WorkType = models.CharField(max_length=50)
    Exp = models.IntegerField()
    WORKER_ID = models.ForeignKey('Workers', on_delete=models.CASCADE)


class Delivery(models.Model):
    Location = models.CharField(max_length=30)
    USER_ID = models.ForeignKey('User', on_delete=models.CASCADE)
    WORKER_ID = models.ForeignKey('Workers', on_delete=models.CASCADE)


class Location(models.Model):
    Latitude = models.FloatField()
    Longitude = models.FloatField()
    DELIVERY_ID = models.ForeignKey(DeliveryBoy, on_delete=models.CASCADE)
