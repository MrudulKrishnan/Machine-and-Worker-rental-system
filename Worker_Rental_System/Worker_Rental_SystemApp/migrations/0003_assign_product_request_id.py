# Generated by Django 3.2.20 on 2023-09-22 04:57

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('Worker_Rental_SystemApp', '0002_workers_gender'),
    ]

    operations = [
        migrations.AddField(
            model_name='assign',
            name='PRODUCT_REQUEST_ID',
            field=models.ForeignKey(default=1, on_delete=django.db.models.deletion.CASCADE, to='Worker_Rental_SystemApp.productrequest'),
            preserve_default=False,
        ),
    ]
