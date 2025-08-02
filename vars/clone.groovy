def call(String url, String branch) {
  echo "Cloning repository from ${url} on branch ${branch}"
  git url: "${url}", branch: "${branch}"
}
