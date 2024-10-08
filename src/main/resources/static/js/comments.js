const blogId = document.getElementById('blogId').value

const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;

const commentsCtnr = document.getElementById('commentCtnr')

const commentForm = document.getElementById('commentForm')
commentForm.addEventListener("submit", handleCommentSubmit)
console.log('CSRF Header Name:', csrfHeaderName);
console.log('CSRF Header Value:', csrfHeaderValue);

const allComments = []

const displayComments = (comments) => {
    commentsCtnr.innerHTML = comments.map(
        (c)=> {
            return asComment(c)
        }
    ).join('')
}

async function handleCommentSubmit(event) {
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;
    const formData = new FormData(form);

    try {
        const responseData = await postFormDataAsJson({url, formData});

        commentsCtnr.insertAdjacentHTML("afterbegin", asComment(responseData));

        form.reset();
    } catch (error) {

        let errorObj = JSON.parse(error.message);

        if (errorObj.fieldWithErrors) {
            errorObj.fieldWithErrors.forEach(
                e => {
                    let elementWithError = document.getElementById(e);
                    if (elementWithError) {
                        elementWithError.classList.add("is-invalid");
                    }
                }

            )
        }

    }
    console.log('going to submit a comment!')
}

async function postFormDataAsJson({url, formData}) {

    const plainFormData = Object.fromEntries(formData.entries());
    const formDataAsJSONString = JSON.stringify(plainFormData);

    const fetchOptions = {
        method: "POST",
        headers: {
            [csrfHeaderName] : csrfHeaderValue,
            "Content-Type" : "application/json",
            "Accept" :"application/json"
        },
        body: formDataAsJSONString
    }
    console.log('Form URL:', url);
    const response = await fetch(url, fetchOptions);

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }

    return response.json();
}

function asComment(c) {
    let commentHtml = `<div id="commentCntr-${c.id}" class="comments">`
    commentHtml += `<h4>${c.user}</h4>`
    commentHtml += `<p>${c.message}</p>`
    commentHtml += `</div>`

    return commentHtml
}

fetch(`http://localhost:8080/api/comments/${blogId}`).
then(response => response.json()).
then(data => {
    for (let comment of data) {
        allComments.push(comment)
    }
    displayComments(allComments)
})