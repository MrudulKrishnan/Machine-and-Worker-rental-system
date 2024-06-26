from django.urls import path
from . import views
urlpatterns = [
    path('', views.login, name="login"),
    path('login_fun', views.login_fun, name="login_fun"),
    path('logout', views.logout, name="logout"),
    path('admin_home', views.admin_home, name="admin_home"),
    path('manage_shop', views.manage_shop, name="manage_shop"),
    path('manage_shop1', views.manage_shop1, name="manage_shop1"),
    path('add_shop', views.add_shop, name="add_shop"),
    path('add_shop_action', views.add_shop_action, name="add_shop_action"),
    path('shop_update/<int:shop_id>', views.shop_update, name="shop_update"),
    path('update_shop_action', views.update_shop_action, name="update_shop_action"),
    path('shop_delete/<int:shop_id>', views.shop_delete, name="shop_delete"),
    path('manage_workers', views.manage_workers, name="manage_workers"),
    path('manage_workers1', views.manage_workers1, name="manage_workers1"),
    path('add_workers', views.add_workers, name="add_workers"),
    path('add_workers_action', views.add_workers_action, name="add_workers_action"),
    path('workers_update/<int:worker_id>', views.workers_update, name="workers_update"),
    path('update_workers_action', views.update_workers_action, name="update_workers_action"),
    path('workers_delete/<int:worker_id>', views.workers_delete, name="workers_delete"),
    path('block_unblock_shop', views.block_unblock_shop, name="block_unblock_shop"),
    path('shop_block/<int:shop_id>', views.shop_block, name="shop_block"),
    path('shop_unblock/<int:shop_id>', views.shop_unblock, name="shop_unblock"),
    path('block_unblock_workers', views.block_unblock_workers, name="block_unblock_workers"),
    path('worker_block/<int:worker_id>', views.worker_block, name="worker_block"),
    path('worker_unblock/<int:worker_id>', views.worker_unblock, name="worker_unblock"),
    path('verify_users', views.verify_users, name="verify_users"),
    path('user_accept/<int:user_id>', views.user_accept, name="user_accept"),
    path('user_reject/<int:user_id>', views.user_reject, name="user_reject"),
    path('search_complaint_type_admin', views.search_complaint_type_admin, name="search_complaint_type_admin"),
    path('view_complaints_reply', views.view_complaints_reply, name="view_complaints_reply"),
    path('complaint_search', views.complaint_search, name="complaint_search"),
    path('view_products', views.view_products, name="view_products"),
    path('search_product_admin', views.search_product_admin, name="search_product_admin"),
    path('view_users', views.view_users, name="view_users"),
    path('view_user_search', views.view_user_search, name="view_user_search"),
    path('logout', views.login, name="logout"),


    #       shop urls

    path('shop_home', views.shop_home, name="shop_home"),
    path('manage_delivery_boy', views.manage_delivery_boy, name="manage_delivery_boy"),
    path('search_delivery_boy', views.search_delivery_boy, name="search_delivery_boy"),
    path('add_delivery_boy', views.add_delivery_boy, name="add_delivery_boy"),
    path('assign_delivery_boy', views.assign_delivery_boy, name="assign_delivery_boy"),
    path('add_delivery_boy', views.add_delivery_boy, name="add_delivery_boy"),
    path('add_delivery_boy_action', views.add_delivery_boy_action, name="add_delivery_boy_action"),
    path('edit_delivery_boy/<int:boy_id>', views.edit_delivery_boy, name="edit_delivery_boy"),
    path('edit_delivery_boy_action', views.edit_delivery_boy_action, name="edit_delivery_boy_action"),
    path('delete_delivery_boy/<int:boy_id>', views.delete_delivery_boy, name="delete_delivery_boy"),
    path('assign/<int:assign_id>', views.assign, name="assign"),
    path('assign_action/<int:boy_id>', views.assign_action, name="assign_action"),
    path('manage_product', views.manage_product, name="manage_product"),
    path('manage_product_search', views.manage_product_search, name="manage_product_search"),
    path('add_product', views.add_product, name="add_product"),
    path('add_product_action', views.add_product_action, name="add_product_action"),
    path('edit_product/<int:product_id>', views.edit_product, name="edit_product"),
    path('edit_product_action', views.edit_product_action, name="edit_product_action"),
    path('delete_product/<int:product_id>', views.delete_product, name="delete_product"),
    path('search_complaint_type_shop', views.search_complaint_type_shop, name="search_complaint_type_shop"),
    path('view_complaints_shop', views.view_complaints_shop, name="view_complaints_shop"),
    path('send_reply_shop/<int:reply_id>', views.send_reply_shop, name="send_reply_shop"),
    path('send_reply_shop_action', views.send_reply_shop_action, name="send_reply_shop_action"),
    path('complaint_search_date', views.complaint_search_date, name="complaint_search_date"),
    path('view_request_update_status', views.view_request_update_status, name="view_request_update_status"),
    path('date_assign/<int:request_id>', views.date_assign, name="date_assign"),
    path('product_accept', views.product_accept, name="product_accept"),
    path('product_reject/<int:request_id>', views.product_reject, name="product_reject"),

    # ////////////////////// webservice workers ///////////////////////////

    path('login_code', views.login_code, name="login_code"),
    path('manage_skill', views.manage_skill, name="manage_skill"),
    path('add_new_skill', views.add_new_skill, name="add_new_skill"),
    path('delete_skill', views.delete_skill, name="delete_skill"),
    path('view_work_request_response', views.view_work_request_response, name="view_work_request_response"),
    path('accept_work_request', views.accept_work_request, name="accept_work_request"),
    path('reject_work_request', views.reject_work_request, name="reject_work_request"),
    path('view_products_send_request', views.view_products_send_request, name="view_products_send_request"),
    path('product_request_send', views.product_request_send, name="product_request_send"),
    path('view_product_name', views.view_product_name, name="view_product_name"),
    path('worker_complaints_reply', views.worker_complaints_reply, name="worker_complaints_reply"),
    path('registration', views.registration, name="registration"),
    path('view_message2', views.view_message2, name="view_message2"),
    path('in_message2', views.in_message2, name="in_message2"),
    path('view_workers', views.view_workers, name="view_workers"),
    path('view_worker_response', views.view_worker_response, name="view_worker_response"),
    path('send_request_for_worker_view', views.send_request_for_worker_view, name="send_request_for_worker_view"),
    path('add_worker_request', views.add_worker_request, name="add_worker_request"),
    path('view_product_response', views.view_product_response, name="view_product_response"),
    path('view_users_for_chat', views.view_users_for_chat, name="view_users_for_chat"),
    path('send_rating', views.send_rating, name="send_rating"),
    path('view_shop_name', views.view_shop_name, name="view_shop_name"),
    path('send_shop_complaint', views.send_shop_complaint, name="send_shop_complaint"),
    path('user_complaints_reply', views.user_complaints_reply, name="user_complaints_reply"),
    path('product_request_send_worker', views.product_request_send_worker, name="product_request_send_worker"),
    path('view_assigned_work', views.view_assigned_work, name="view_assigned_work"),
    path('search_worker', views.search_worker, name="search_worker"),
    path('search_product_user', views.search_product_user, name="search_product_user"),
    path('search_complaint_user', views.search_complaint_user, name="search_complaint_user"),
    path('search_skill', views.search_skill, name="search_skill"),
    path('search_products_send_request', views.search_products_send_request, name="search_products_send_request"),
    path('search_work_request_response', views.search_work_request_response, name="search_work_request_response"),
    path('search_worker_complaints_reply', views.search_worker_complaints_reply, name="search_worker_complaints_reply"),
    path('search_assigned_work', views.search_assigned_work, name="search_assigned_work"),
    path('update_location', views.update_location, name="update_location"),




]

