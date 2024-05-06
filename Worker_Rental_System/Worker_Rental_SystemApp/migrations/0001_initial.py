# Generated by Django 3.2.20 on 2023-09-20 10:17

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Login',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Username', models.CharField(max_length=20)),
                ('Password', models.CharField(max_length=20)),
                ('Type', models.CharField(max_length=20)),
            ],
        ),
        migrations.CreateModel(
            name='Product',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Product_Type', models.CharField(max_length=30)),
                ('Product_Name', models.CharField(max_length=30)),
                ('Product_Details', models.CharField(max_length=30)),
                ('Price_per_day', models.CharField(max_length=30)),
                ('Image', models.ImageField(upload_to='')),
                ('Stock', models.IntegerField()),
            ],
        ),
        migrations.CreateModel(
            name='User',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('FirstName', models.CharField(max_length=20)),
                ('LastName', models.CharField(max_length=20)),
                ('Age', models.IntegerField()),
                ('Gender', models.CharField(max_length=20)),
                ('Place', models.CharField(max_length=20)),
                ('Post', models.CharField(max_length=20)),
                ('Pin', models.IntegerField()),
                ('Phone', models.BigIntegerField()),
                ('Email', models.CharField(max_length=20)),
                ('LOGIN_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.login')),
            ],
        ),
        migrations.CreateModel(
            name='Workers',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('FirstName', models.CharField(max_length=20)),
                ('LastName', models.CharField(max_length=20)),
                ('Age', models.IntegerField()),
                ('Place', models.CharField(max_length=20)),
                ('Post', models.CharField(max_length=20)),
                ('Pin', models.IntegerField()),
                ('Phone', models.BigIntegerField()),
                ('Field', models.CharField(max_length=20)),
                ('LOGIN_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.login')),
            ],
        ),
        migrations.CreateModel(
            name='WorkersRequest',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Date', models.CharField(max_length=20)),
                ('Status', models.CharField(max_length=20)),
                ('Work_Details', models.CharField(max_length=20)),
                ('USER_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.user')),
                ('WORKER_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.workers')),
            ],
        ),
        migrations.CreateModel(
            name='Skills',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Skill', models.CharField(max_length=50)),
                ('Date', models.CharField(max_length=20)),
                ('WorkType', models.CharField(max_length=50)),
                ('Exp', models.IntegerField()),
                ('WORKER_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.workers')),
            ],
        ),
        migrations.CreateModel(
            name='Shop',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Name', models.CharField(max_length=20)),
                ('Place', models.CharField(max_length=20)),
                ('Post', models.CharField(max_length=20)),
                ('Pin', models.CharField(max_length=20)),
                ('Phone', models.BigIntegerField()),
                ('Email', models.CharField(max_length=20)),
                ('LOGIN_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.login')),
            ],
        ),
        migrations.CreateModel(
            name='Ratings',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Rating', models.CharField(max_length=20)),
                ('Date', models.CharField(max_length=20)),
                ('Review', models.CharField(max_length=100)),
                ('LOGIN_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.login')),
            ],
        ),
        migrations.CreateModel(
            name='ProductRequest',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Date', models.CharField(max_length=20)),
                ('Status', models.CharField(max_length=20)),
                ('ReturnDate', models.CharField(max_length=20)),
                ('PRODUCT_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.product')),
                ('USER_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.user')),
            ],
        ),
        migrations.AddField(
            model_name='product',
            name='SHOP_ID',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.shop'),
        ),
        migrations.CreateModel(
            name='DeliveryBoy',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('FirstName', models.CharField(max_length=100)),
                ('LastName', models.CharField(max_length=100)),
                ('Age', models.IntegerField()),
                ('Gender', models.CharField(max_length=100)),
                ('Place', models.CharField(max_length=100)),
                ('Post', models.CharField(max_length=100)),
                ('Pin', models.IntegerField()),
                ('Phone', models.BigIntegerField()),
                ('Email', models.CharField(max_length=100)),
                ('LOGIN_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.login')),
            ],
        ),
        migrations.CreateModel(
            name='Delivery',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Location', models.CharField(max_length=30)),
                ('USER_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.user')),
                ('WORKER_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.workers')),
            ],
        ),
        migrations.CreateModel(
            name='Complaint',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Complaints', models.CharField(max_length=100)),
                ('Date', models.CharField(max_length=20)),
                ('Reply', models.CharField(max_length=100)),
                ('LOGIN_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.login')),
                ('SHOP_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.shop')),
            ],
        ),
        migrations.CreateModel(
            name='Chat',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Message', models.CharField(max_length=100)),
                ('Date', models.CharField(max_length=100)),
                ('Time', models.CharField(max_length=100)),
                ('USER_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.user')),
                ('WORKER_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.workers')),
            ],
        ),
        migrations.CreateModel(
            name='Assign',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Date', models.CharField(max_length=20)),
                ('Status', models.CharField(max_length=20)),
                ('DELIVERY_BOY_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.deliveryboy')),
                ('PRODUCT_ID', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.product')),
            ],
        ),
    ]