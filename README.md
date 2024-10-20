# <p style="color: red;">Test User Endpoints</p>

## 1. add user
## 2. update user
- Also, Try to send update request with different username (security issue to prevent user from falsify the username or userId in request and update other user's credentials)
## 3. delete user
## 4. authenticate

# <p style="color: red;">Test Faculty Endpoints</p>

## 1. add faculty
- Try to send request with a non admin
## 2. delete faculty
- Try to send request with a non admin

# <p style="color: red;">Resume Endpoints</p>
## 1. generate
- Test template1 and template2

# <p style="color: red;">Test Quizz Endpoints</p>

## 1. add quizz
- Try to send request with a non admin
- Try to add quizz without questions

## 2. delete quizz
- Try to send request with a non admin
- Check if all related questions and theirs choices are deleted

## 3. update quizz
- Try to send request with a non admin
- Try to send questions with that request and verify that is not allowed to update or add questions with that endpoint

## 4. add question
- Try to send request with a non admin
- Try set a not found quizzId

## 5. update question
- Try to send request with a non admin
- Try to change the quizzId in the request
- Try to send choices with that request and verify that is not allowed to update or add choices with that endpoint

## 6. add choice:
- Try to send request with a non admin
- Try set a not found questionId

## 7. update choice:
- Try to send request with a non admin
- Try to change questionId

## 8. delete choice
- Try to send request with a non admin
- Try to set not found questionId
- Try to set not found quizzId


## <p style="color: green;">these endpoints don't need authentication</p>
``
/swagger-ui/**
``

``
/v3/api-docs/**
``

``
/api/user/add
``

``
/api/user/authenticate
``