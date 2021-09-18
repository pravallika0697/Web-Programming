function getGithubInfo(user) {
    $('.information').text("Searching");
    $.get(`https://api.github.com/users/${user}/events/public`)
        .done(users => showUser(users[0]))
        .fail(() => noSuchUser(user));
    $.get(`https://api.github.com/users/${user}/repos`)
        .done(repos => showRepos(repos));
}

// display the specified user's profile
function showUser(user) {
    $('#results, #results .result, #results .card-header').removeClass("collapse");
    $('.avatar').html("<img style='width: 170px; height: 170px;' src='" + user.actor.avatar_url + "' alt='Avatar'>")
    const profile = "https://github.com/" + encodeURIComponent(user.actor.login);
    $('.information').html("<a href='" + profile + "'>" + user.actor.login + "</a>")
    $('.name').text(user.actor.login)
    $('#id').text(user.actor.id)
    $('#last').text(user.created_at)

    // returns the length of followers
    $.get(`https://api.github.com/users/${user.actor.login}/followers`).done(follower => {
        $('#followers').empty().append($('<span/>').text("Followers: " + follower.length.toString()).addClass('badge badge-primary mr-1'));
    })

    // returns the length of followings
    $.get(`https://api.github.com/users/${user.actor.login}/following`).done(following => {
        $('#following').empty().append($('<span/>').text("Following: " + following.length.toString()).addClass('badge badge-success mr-1'));
    })

    // returns user's company
    $.get(`https://api.github.com/users/${user.actor.login}`).done(user => {
        $('#company').empty().append($('<span/>').text(user.company));
    })

    // returns user's blog
    $.get(`https://api.github.com/users/${user.actor.login}`).done(user => {
        $('#blog').empty().append($('<span/>').text(user.blog));
    })

    // returns length of the repositories
    $.get(`https://api.github.com/users/${user.actor.login}`).done(user => {
        $('#rep').empty().append($('<span/>').text(user.public_repos));
    })
}

// display message if no user found
function noSuchUser(username) {
    $('#results, #results .card-header').removeClass("collapse");
    $('#results .result').addClass("collapse");
    $('.name').text("We couldn't find any users matching : "+ username);
}

// display repositories of user
function showRepos(repos) {
    let output = $('.repos').empty();

    for (const repo of repos) {
        output.append($('<span/>').text(repo.name).addClass('badge badge-info mr-1'));
    }
}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        if (e.which === 13) {
            username = $(this).val();
            $(this).val("");
            getGithubInfo(username);
        }
    })
});