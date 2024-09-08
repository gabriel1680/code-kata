import re
from typing import Callable


def test_password_should_not_have_less_than_8_char():
    password = "xxxxxxx"
    assert False == validate_password(password)
  
def test_password_should_not_contains_only_lower_case_letters():
    password = "xxxxxxxx"
    assert False == validate_password(password)
  
def test_password_should_not_contains_only_upper_case_letters():
    password = "XXXXXXXX"
    assert False == validate_password(password)
  
def test_password_should_have_at_least_one_number():
    password = "xxxXXXxx"
    assert False == validate_password(password)

def test_password_should_have_at_least_one_letter():
    password = "111111111"
    assert False == validate_password(password)

def test_password_should_have_at_least_one_underscore():
    password = "Xx1xxxxx"
    assert False == validate_password(password)

def test_password_should_be_ok():
    password = "Password4_8"
    assert True == validate_password(password)


def validate_password(password: str) -> bool:

    if have_less_than_8_len(password):
        return False

    if not_have_lower_letter(password):
        return False

    if not_have_upper_letter(password):
        return False

    if not_have_number(password):
        return False

    if not_have_underscore(password):
        return False

    return True

def have_less_than_8_len(password: str) -> bool:
    return len(password) < 8

def not_have_lower_letter(password: str) -> bool:
    return re.search("[a-z]", password) is None

def not_have_upper_letter(password: str) -> bool:
    return re.search("[A-Z]", password) is None

def not_have_number(password: str) -> bool:
    return re.search("[0-9]", password) is None

def not_have_underscore(password: str) -> bool:
    return re.search("[_]", password) is None

