"""This module interacts with Database of the Content Management system and returns the cursor object.
"""

__author__ = 'harsh.hajela85@gmail.com (Harsh Hajela)'

from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from sqlalchemy import Column, Integer, Table, MetaData, String, ForeignKey

#Database name
DB_ENGINE = 'mysql'
DB_USER = 'root'
DB_PASSWORD = 12345
DB_HOST = 'localhost'
DB_NAME = 'CMS_DB'
BASE = None

engine_text = '%s://%s:%s@%s/%s' % (DB_ENGINE, DB_USER, DB_PASSWORD,
                                    DB_HOST, DB_NAME)
try:
    database_engine = create_engine(engine_text, echo = False)
except:
    raise "Can't connect to database."
#Creating Base and session object
Base = declarative_base(database_engine)
Session = sessionmaker(bind=database_engine)
session = Session()

class UserRoles(Base):
    __tablename__ = 'userroles'
    __table_args__ = {'autoload':True}

    user_role_name = Column(String)
    user_role_id = Column(Integer, primary_key=True)
  
    def __init__(self, user_role_id, user_role_name=None):
        self.user_role_name = user_role_name
        self.user_role_id = user_role_id
  
    def __repr__(self):
        return "<UserRoles('%s', user_role_id:'%s')>" % (self.user_role_name, self.user_role_id)

class Users(Base):
    """"""
    __tablename__ = 'users'
    __table_args__ = {'autoload':True}

    user_email_id = Column(String, primary_key=True)
    user_name = Column(String)
    user_role_id = Column(Integer, ForeignKey('userroles.user_role_id'))

    def __init__(self, user_email_id, user_name, user_role_id=None):
        self.user_name = user_name
        self.user_email_id = user_email_id
        self.user_role_id = user_role_id

    def __repr__(self):
        return "<User('%s', user_role_id:'%s')>" % (self.user_name, self.user_role_id)

class AccessOperations(Base):
    __tablename__ = 'accessoperations'
    __table_args__ = {'autoload':True}

    operation_name = Column(String)
    operation_id = Column(Integer, primary_key=True)

    def __init__(self, operation_id, operation_name=None):
        self.operation_name = operation_name
        self.operation_id = operation_id

    def __repr__(self):
        return "<AccessOperations('%s', user_role_id:'%s')>" % (self.operation_name, self.operation_id)

class Assets(Base):
    __tablename__ = 'assets'
    __table_args__ = {'autoload':True}

    asset_name = Column(String)
    asset_id = Column(Integer, primary_key=True)

    def __init__(self, asset_id, asset_name=None):
        self.asset_name = asset_name
        self.asset_id = asset_id

    def __repr__(self):
        return "<Assets('%s', asset_id:'%s')>" % (self.asset_name, self.asset_id)

class UserAssociation(Base):
    __tablename__ = 'user_access_asset_operations'
    __table_args__ = {'autoload':True}

#     id = Column(Integer, primary_key=True)
    user_role_id = Column(Integer, ForeignKey('userroles.user_role_id'))
    operation_id = Column(Integer, ForeignKey('accessoperations.operation_id'))
    asset_id = Column(Integer, ForeignKey('assets.asset_id'))

    def __init__(self, user_role_id, asset_id, operation_id):
        self.user_role_id = user_role_id
        self.asset_id = asset_id
        self.operation_id = operation_id

    def __repr__(self):
        return "<(UserAssociation:'%s', '%s', '%s')>" % (self.user_role_id, self.asset_id, self.operation_id)

class Skin(Base):
    __tablename__ = 'skin'
    __table_args__ = {'autoload':True}

    skin_name = Column(String)
    skin_id = Column(Integer, primary_key=True)

    def __init__(self, skin_id, skin_name=None):
        self.skin_id = skin_name
        self.skin_id = skin_id

    def __repr__(self):
        return "<Skin('%s', skin_id:'%s')>" % (self.skin_name, self.skin_id)

class SupportedLanguages(Base):
    __tablename__ = 'supportedlanguages'
    __table_args__ = {'autoload':True}

    lang_name = Column(String)
    lang_locale = Column(String)
    lang_id = Column(Integer, primary_key=True)

    def __init__(self, lang_id, lang_name=None, lang_locale=None):
        self.lang_name = lang_name
        self.lang_id = lang_id

    def __repr__(self):
        return "<Language('%s', lang_id:'%s')>" % (self.lang_name, self.lang_id)

class DigitalAssets(Base):
    __tablename__ = 'digitalassets'
    __table_args__ = {'autoload':True}

    digital_asset_name = Column(String)
    digital_asset_path = Column(String)
    digital_asset_id = Column(Integer, primary_key=True)
    digital_asset_soft_delete = Column(Integer)
    digital_asset_lang_id = Column(Integer, ForeignKey('supportedlanguage.lang_id'))
    digital_asset_skin_id = Column(Integer, ForeignKey('skin.skin_id'))

    def __init__(self, digital_asset_id, digital_asset_name=None):
        self.digital_asset_name = digital_asset_name
        self.digital_asset_id = digital_asset_id

    def __repr__(self):
        return "<Language('%s', digital_asset_id:'%s')>" % (
            self.digital_asset_name, self.digital_asset_id)

class TextAssets(Base):
    __tablename__ = 'textassets'
    __table_args__ = {'autoload':True}

    text_asset_name = Column(String)
    text_asset_path = Column(String)
    text_asset_id = Column(Integer, primary_key=True)
    text_asset_soft_delete = Column(Integer)
    text_asset_lang_id = Column(Integer, ForeignKey('supportedlanguage.lang_id'))
    text_asset_skin_id = Column(Integer, ForeignKey('skin.skin_id'))

    def __init__(self, text_asset_id, text_asset_name=None):
        self.text_asset_name = text_asset_name
        self.text_asset_id = text_asset_id

    def __repr__(self):
        return "<TextAsset('%s', text_asset_id:'%s')>" % (self.text_asset_name,
                                                          self.text_asset_id)

class Banner(Base):
    __tablename__ = 'banner'
    __table_args__ = {'autoload':True}

    banner_name = Column(String)
    banner_details = Column(String)
    banner_id = Column(Integer, primary_key=True)
    banner_soft_delete = Column(Integer)

    def __init__(self, banner_id, banner_name=None):
        self.banner_name = banner_name
        self.banner_id = banner_id

    def __repr__(self):
        return "<Banner('%s', banner_id:'%s')>" % (self.banner_name,
                                                   self.banner_id)

class BannerAssociation(Base):
    __tablename__ = 'banner_text_digital'
    __table_args__ = {'autoload':True}

    id = Column(Integer, primary_key=True)
    banner_id = Column(Integer, ForeignKey('banner.banner_id'))
    text_asset_id = Column(Integer, ForeignKey('textassets.text_asset_id'))
    digital_asset_id = Column(Integer, ForeignKey('digitalassets.digital_asset_id'))
    soft_delete = Column(Integer)

    def __init__(self, banner_id, text_asset_id, digital_asset_id):
        self.banner_id = banner_id
        self.text_asset_id = text_asset_id
        self.digital_asset_id = digital_asset_id

    def __repr__(self):
        return "<(BannerAssociation:'%s', '%s', '%s')>" % (self.banner_id,
               self.text_asset_id, self.digital_asset_id)

