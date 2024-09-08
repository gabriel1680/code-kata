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

# ===== impl ====

def have_more_than_8_len(password: str) -> bool:
    return len(password) >= 8

def contains(pattern: str, text: str) -> bool:
    return re.search(pattern, text) is not None

def have_lower_letter(password: str) -> bool:
    return contains("[a-z]", password)

def have_upper_letter(password: str) -> bool:
    return contains("[A-Z]", password)

def have_number(password: str) -> bool:
    return contains("[0-9]", password)

def have_underscore(password: str) -> bool:
    return contains("[_]", password)

validators = [
    have_more_than_8_len,
    have_lower_letter,
    have_upper_letter,
    have_number,
    have_underscore
]

def validate_password(password: str) -> bool:
    for rule in validators:
        if rule(password) == False:
            return False
    return True

