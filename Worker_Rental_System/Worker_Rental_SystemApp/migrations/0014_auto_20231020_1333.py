# Generated by Django 3.2.20 on 2023-10-20 08:03

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('Worker_Rental_SystemApp', '0013_alter_complaint_complaints'),
    ]

    operations = [
        migrations.AddField(
            model_name='workers',
            name='Latitude',
            field=models.FloatField(default=1),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='workers',
            name='Longitude',
            field=models.FloatField(default=1),
            preserve_default=False,
        ),
    ]