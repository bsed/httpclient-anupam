"""This module is the controller for Content Management system.
When executed it asks 3 things from user.
    1) User Name
    2) Asset on which user want to work.
    3) Operation which user want to perform on asset.

Depending on the access privileges, relavant data will be asked and operation will be performed.

This module is just a work around to use the API's. In real world, JSON messages will be parsed here
and appropriate API's will be called from here to perform the CMS operations. No validations have been done
on the input parameters.
 
"""

__author__ = 'harsh.hajela85@gmail.com (Harsh Hajela)'

import cms_api

#Details to be shown to user at the time of taking inputs.
user_text = "Please enter user name:\n"

asset_text = """Please enter Asset on which user would like to work on: (Enter number only)
1 (text)
2) (digital)
3) (banner)
4) (language)
"""

operation_text = """Please enter the operation which user would like to perform: (Enter number only)
1 (Search)
2 (Add)
3 (Remove)
4 (Modify)
"""
action_text = """Please enter what action you would like to perform (Enter Number)
1 Check User Access Rights.
2 Add New User and roles to it.
"""

add_role_text = """Enter Role for this user (Enter Number)
1 (Designer)
2 (Content Manager)
3 (Casino Manager)
"""

email_text = """Enter user email id
"""

def main():
    action = int(raw_input(action_text))

    if action == 1:
        user = raw_input(user_text)
        asset = raw_input(asset_text)
        operation = raw_input(operation_text)
        print 'Checking if %s has access on %s asset to perform %s operation.' % (user, asset, operation)
        #### comments
        if cms_api.checkUserAccessRights(user, asset, operation):
            print 'User can perform this operation.'
        else:
            print "User doesn't have access privileges to perform this operation" 

    elif action == 2:
        user = raw_input(user_text)
        email = raw_input(email_text)
        role_name = raw_input(add_role_text)
        if cms_api.addUser(user, email, role_name):
            print 'User %s added successfully' % user
        else:
            print "Can't add this user."

if  __name__ =='__main__':
    main()