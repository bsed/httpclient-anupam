"""This module provides API's to Content Management system.
"""

__author__ = 'harsh.hajela85@gmail.com (Harsh Hajela)'

import cms_db_model as model

def checkUserAccessRights(user, asset, operation):
    """ Checks the access details of the user to perform the operation as
        per the role assigned to user.

    Args:
        user: User name for which access has to be checked.
        asset: The id of the asset on which user want to perform the operation.
               These can be text(1), digital(2), banner(3), language(4), etc.
        operation: The id of the operation.
                   these can be Search(1), Add(2), Remove(3), Modify(4) etc.

    Returns:
        A boolean value is returned. If user has access then it will return
        True, otherwise False is returned.
    """

    # Querying on the UserAssocition table to check if the user has access
    # previliges to perform operation on the asset.
    query = model.session.query(model.UserAssociation).join(model.UserRoles).\
                join(model.Users).join(model.Assets).join(
                model.AccessOperations).filter(model.Users.user_name == user).\
                filter(model.Assets.asset_id == asset).filter(
                model.AccessOperations.operation_id == operation)

    if query.all():
        return True

    return False

def addUser(user_name, user_email, role_id):
    """ This function adds a user in database and assign role to it.
        The roles are predefined in the database and the same should be
        applied to the user.

    Args:
        user_name: User name for which access has to be checked.
        user_email: Email id of the new user.
        role_id: Role id of the new user.

    Returns:
        A boolean value is returned. If user is added successfully then it
        returns true, else false is returned.
    """

    #Creating class for a new user
    new_user = model.Users(user_email_id = user_email, user_name = user_name,
                           user_role_id = role_id)
    # Querying on the User table. The enail id is the primary key.
    user_exist_query = model.session.query(model.Users).filter(
                            model.Users.user_email_id == new_user.user_email_id)

    #Checking if this user already exist.
    if user_exist_query.all():
        print 'User already exist in the database'
        return False

    #Now we add the user
    model.session.add(new_user)
    # Saving the session data in database.
    try:
        model.session.commit()
    # Catching exception here.
    except:
        model.session.flush()
        return False

    return True
#===============================================================================
#     # Getting table objects
#     user_table = db_obj.users
#     roles_table = db_obj.user_roles
# 
#     #Checking if this user with role is already present in database.
#     check_statement = select([user_table.c.user_role_id]).select_from(user_table.join(
#             roles_table)).where(and_(roles_table.c.user_role_name == role_name,
#                                      user_table.c.user_name == user))
# 
#     #Getting the role id
#     role_id = db_obj.connection.execute(check_statement).first()
#     if role_id:
#         print 'User already exists with same name and role id.'
#         return False
# 
#     else:
#         # Adding this user in database
#         insert_statement = user_table.insert().values(user_name = user,
#                                                       user_role_id = role_id[0])
#         #The following statement will COMMIT the result.
#         result = db_obj.connection.execute(insert_statement)
#         print result
#         return True
#===============================================================================
