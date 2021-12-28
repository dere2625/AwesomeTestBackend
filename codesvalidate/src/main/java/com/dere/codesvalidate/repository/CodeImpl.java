package com.dere.codesvalidate.repository;

import com.dere.codesvalidate.models.Code;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public class CodeImpl implements CodeRepository {
    @Override
    public <S extends Code> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Code> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Code> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Code> findAll() {
        return null;
    }

    @Override
    public Iterable<Code> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Code entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Code> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Code> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Code> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Code> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Code> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Code> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Code> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Code> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Code> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Code> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Code> boolean exists(Example<S> example) {
        return false;
    }
}
